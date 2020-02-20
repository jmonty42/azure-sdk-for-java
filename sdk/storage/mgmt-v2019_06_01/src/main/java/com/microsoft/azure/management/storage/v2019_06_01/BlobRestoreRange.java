/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.storage.v2019_06_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Blob range.
 */
public class BlobRestoreRange {
    /**
     * Blob start range. Empty means account start.
     */
    @JsonProperty(value = "startRange", required = true)
    private String startRange;

    /**
     * Blob end range. Empty means account end.
     */
    @JsonProperty(value = "endRange", required = true)
    private String endRange;

    /**
     * Get blob start range. Empty means account start.
     *
     * @return the startRange value
     */
    public String startRange() {
        return this.startRange;
    }

    /**
     * Set blob start range. Empty means account start.
     *
     * @param startRange the startRange value to set
     * @return the BlobRestoreRange object itself.
     */
    public BlobRestoreRange withStartRange(String startRange) {
        this.startRange = startRange;
        return this;
    }

    /**
     * Get blob end range. Empty means account end.
     *
     * @return the endRange value
     */
    public String endRange() {
        return this.endRange;
    }

    /**
     * Set blob end range. Empty means account end.
     *
     * @param endRange the endRange value to set
     * @return the BlobRestoreRange object itself.
     */
    public BlobRestoreRange withEndRange(String endRange) {
        this.endRange = endRange;
        return this;
    }

}