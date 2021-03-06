// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.authorization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.azure.resourcemanager.authorization.fluent.models.KeyCredentialInner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** KeyCredential list operation result. */
@Fluent
public final class KeyCredentialListResult {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(KeyCredentialListResult.class);

    /*
     * A collection of KeyCredentials.
     */
    @JsonProperty(value = "value")
    private List<KeyCredentialInner> value;

    /**
     * Get the value property: A collection of KeyCredentials.
     *
     * @return the value value.
     */
    public List<KeyCredentialInner> value() {
        return this.value;
    }

    /**
     * Set the value property: A collection of KeyCredentials.
     *
     * @param value the value value to set.
     * @return the KeyCredentialListResult object itself.
     */
    public KeyCredentialListResult withValue(List<KeyCredentialInner> value) {
        this.value = value;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (value() != null) {
            value().forEach(e -> e.validate());
        }
    }
}
