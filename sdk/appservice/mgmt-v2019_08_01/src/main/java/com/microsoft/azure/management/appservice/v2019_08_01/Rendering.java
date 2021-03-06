/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.appservice.v2019_08_01;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Instructions for rendering the data.
 */
public class Rendering {
    /**
     * Rendering Type. Possible values include: 'NoGraph', 'Table',
     * 'TimeSeries', 'TimeSeriesPerInstance'.
     */
    @JsonProperty(value = "type")
    private RenderingType type;

    /**
     * Title of data.
     */
    @JsonProperty(value = "title")
    private String title;

    /**
     * Description of the data that will help it be interpreted.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * Get rendering Type. Possible values include: 'NoGraph', 'Table', 'TimeSeries', 'TimeSeriesPerInstance'.
     *
     * @return the type value
     */
    public RenderingType type() {
        return this.type;
    }

    /**
     * Set rendering Type. Possible values include: 'NoGraph', 'Table', 'TimeSeries', 'TimeSeriesPerInstance'.
     *
     * @param type the type value to set
     * @return the Rendering object itself.
     */
    public Rendering withType(RenderingType type) {
        this.type = type;
        return this;
    }

    /**
     * Get title of data.
     *
     * @return the title value
     */
    public String title() {
        return this.title;
    }

    /**
     * Set title of data.
     *
     * @param title the title value to set
     * @return the Rendering object itself.
     */
    public Rendering withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get description of the data that will help it be interpreted.
     *
     * @return the description value
     */
    public String description() {
        return this.description;
    }

    /**
     * Set description of the data that will help it be interpreted.
     *
     * @param description the description value to set
     * @return the Rendering object itself.
     */
    public Rendering withDescription(String description) {
        this.description = description;
        return this;
    }

}
