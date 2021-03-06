// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.communication.sms.samples.quickstart;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.azure.communication.common.PhoneNumber;
import com.azure.communication.common.CommunicationClientCredential;
import com.azure.communication.sms.SmsClient;
import com.azure.communication.sms.SmsClientBuilder;
import com.azure.communication.sms.models.SendSmsOptions;
import com.azure.communication.sms.models.SendSmsResponse;
import com.azure.core.http.HttpClient;
import com.azure.core.http.netty.NettyAsyncHttpClientBuilder;

/**
 * Hello world!
 *
 */
public class ReadmeSamples {
    public static void main(String[] args) {

        // Your can find your endpoint and access key from your resource in the Azure Portal
        String endpoint = "https://<RESOURCE_NAME>.communication.azure.com";
        String accessKey = "SECRET";

        // Instantiate the http client
        HttpClient httpClient = new NettyAsyncHttpClientBuilder().build();
        
        CommunicationClientCredential credential = null;
        try {
            credential = new CommunicationClientCredential(accessKey);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println(e.getMessage());
        }
        
        // Create a new SmsClientBuilder to instantiate an SmsClient
        SmsClientBuilder smsClientBuilder = new SmsClientBuilder();

        // Set the endpoint, access key, and the HttpClient
        smsClientBuilder.endpoint(endpoint)
            .credential(credential)
            .httpClient(httpClient);

        // Build a new SmsClient
        SmsClient smsClient = smsClientBuilder.buildClient();

        // Currently Sms services only supports one phone number
        List<PhoneNumber> to = new ArrayList<PhoneNumber>();
        to.add(new PhoneNumber("<to-phone-number>"));

        // SendSmsOptions is an optional field. It can be used
        // to enable a delivery report to the Azure Event Grid
        SendSmsOptions options = new SendSmsOptions();
        options.setEnableDeliveryReport(true);

        // Send the message and check the response for a message id
        SendSmsResponse response = smsClient.sendMessage(
            new PhoneNumber("<leased-phone-number>"), 
            to, 
            "your message",
            options /* Optional */);

        System.out.println("MessageId: " + response.getMessageId());
    }
}
