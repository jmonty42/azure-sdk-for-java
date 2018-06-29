/*
 * Copyright Microsoft Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.microsoft.azure.storage;

import com.microsoft.azure.storage.blob.RetryReader;
import com.microsoft.azure.storage.blob.models.BlobsDownloadHeaders;
import com.microsoft.azure.storage.blob.models.StorageErrorException;
import com.microsoft.rest.v2.RestResponse;
import com.microsoft.rest.v2.http.BufferedHttpResponse;
import com.microsoft.rest.v2.http.HttpHeaders;
import com.microsoft.rest.v2.http.HttpResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class RetryReaderMockFlowable extends Flowable<ByteBuffer> {

    public static final int RR_TEST_SCENARIO_SUCCESSFUL_ONE_CHUNK = 0;

    public static final int RR_TEST_SCENARIO_SUCCESSFUL_MULTI_CHUNK = 1;

    public static final int RR_TEST_SCENARIO_SUCCESSFUL_STREAM_FAILURES = 2;

    public static final int RR_TEST_SCENARIO_MAX_RETRIES_EXCEEDED = 3;

    public static final int RR_TEST_SCENARIO_NON_RETRYABLE_ERROR = 4;

    public static final int RR_TEST_SCENARIO_ERROR_GETTER_INITIAL = 5;

    public static final int RR_TEST_SCENARIO_ERROR_GETTER_MIDDLE = 6;

    private int scenario;

    private int tryNumber;

    private RetryReader.HTTPGetterInfo info;

    private ByteBuffer scenarioData;

    public ByteBuffer getScenarioData() {
        return this.scenarioData;
    }

    public int getTryNumber() {
        return this.tryNumber;
    }

    public RetryReaderMockFlowable(int scenario) {
        this.scenario = scenario;
        switch(this.scenario) {
            case RR_TEST_SCENARIO_SUCCESSFUL_ONE_CHUNK:
                this.scenarioData = APISpec.getRandomData(512*1024);
                break;
            case RR_TEST_SCENARIO_SUCCESSFUL_MULTI_CHUNK:
                this.scenarioData = APISpec.getRandomData(1024);
                break;
            case RR_TEST_SCENARIO_SUCCESSFUL_STREAM_FAILURES:
                this.scenarioData = APISpec.getRandomData(1024);
                break;
        }
    }

    @Override
    protected void subscribeActual(Subscriber<? super ByteBuffer> s) {
        switch(this.scenario) {
            case RR_TEST_SCENARIO_SUCCESSFUL_ONE_CHUNK:
                s.onNext(this.scenarioData.duplicate());
                s.onComplete();
                break;

            case RR_TEST_SCENARIO_SUCCESSFUL_MULTI_CHUNK:
                for (int i=0; i<4; i++) {
                    ByteBuffer toSend = this.scenarioData.duplicate();
                    toSend.position(i*256);
                    toSend.limit((i+1)*256);
                    s.onNext(toSend);
                }
                s.onComplete();
                break;

            case RR_TEST_SCENARIO_SUCCESSFUL_STREAM_FAILURES:
                if (this.tryNumber <= 3) {
                    // tryNumber is 1 indexed, so we have to sub 1.
                    if (this.info.offset != (this.tryNumber-1)*256 ||
                            this.info.count != this.scenarioData.remaining() - (this.tryNumber-1) * 256) {
                        s.onError(new IllegalArgumentException("Info values are incorrect."));
                        return;
                    }
                    ByteBuffer toSend = this.scenarioData.duplicate();
                    toSend.position((this.tryNumber-1)*256);
                    toSend.limit(this.tryNumber*256);
                    s.onNext(toSend);
                    s.onError(new IOException());
                    break;
                }
                if (this.info.offset != (this.tryNumber-1)*256 ||
                        this.info.count != this.scenarioData.remaining() - (this.tryNumber-1) * 256) {
                    s.onError(new IllegalArgumentException("Info values are incorrect."));
                    return;
                }
                ByteBuffer toSend = this.scenarioData.duplicate();
                toSend.position((this.tryNumber-1)*256);
                toSend.limit(this.tryNumber*256);
                s.onNext(toSend);
                s.onComplete();
                break;

            case RR_TEST_SCENARIO_MAX_RETRIES_EXCEEDED:
                s.onError(new IOException());
                break;

            case RR_TEST_SCENARIO_NON_RETRYABLE_ERROR:
                s.onError(new Exception());
                break;

            case RR_TEST_SCENARIO_ERROR_GETTER_MIDDLE:
                s.onError(new IOException());
        }
    }

    public Single<? extends RestResponse<?, Flowable<ByteBuffer>>> getter(RetryReader.HTTPGetterInfo info) {
        this.tryNumber++;
        this.info = info;

        switch(this.scenario) {
            case RR_TEST_SCENARIO_ERROR_GETTER_INITIAL:
                throw new Error("Getter error", new IOException());
            case RR_TEST_SCENARIO_ERROR_GETTER_MIDDLE:
                switch (this.tryNumber) {
                    case 1:
                        RestResponse<BlobsDownloadHeaders, Flowable<ByteBuffer>> response =
                                new RestResponse<>(200, new BlobsDownloadHeaders(), new HashMap<>(), this);
                        return Single.just(response);
                    case 2:
                        // This validates that we don't retry in the getter even if it's an error from the service.
                        throw new Error("GetterError", new StorageErrorException("Message", new HttpResponse() {
                            @Override
                            public int statusCode() {
                                return 0;
                            }

                            @Override
                            public String headerValue(String s) {
                                return null;
                            }

                            @Override
                            public HttpHeaders headers() {
                                return null;
                            }

                            @Override
                            public Flowable<ByteBuffer> body() {
                                return null;
                            }

                            @Override
                            public Single<byte[]> bodyAsByteArray() {
                                return null;
                            }

                            @Override
                            public Single<String> bodyAsString() {
                                return null;
                            }
                        }));
                    default:
                        throw new IllegalArgumentException("Retried after error in getter");
                }
            default:
                RestResponse<BlobsDownloadHeaders, Flowable<ByteBuffer>> response =
                        new RestResponse<>(200, new BlobsDownloadHeaders(), new HashMap<>(), this);
                return Single.just(response);
        }
    }
}
