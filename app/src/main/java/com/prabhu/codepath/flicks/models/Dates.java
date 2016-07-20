
package com.prabhu.codepath.flicks.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dates {

    @JsonProperty("maximum")
    private String maximum;
    @JsonProperty("minimum")
    private String minimum;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * 
     * @return
     *     The maximum
     */
    @JsonProperty("maximum")
    public String getMaximum() {
        return maximum;
    }

    /**
     * 
     * @param maximum
     *     The maximum
     */
    @JsonProperty("maximum")
    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    /**
     * 
     * @return
     *     The minimum
     */
    @JsonProperty("minimum")
    public String getMinimum() {
        return minimum;
    }

    /**
     * 
     * @param minimum
     *     The minimum
     */
    @JsonProperty("minimum")
    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
