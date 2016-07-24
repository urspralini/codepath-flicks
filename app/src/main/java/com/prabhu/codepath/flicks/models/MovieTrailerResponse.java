
package com.prabhu.codepath.flicks.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieTrailerResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("quicktime")
    private List<Object> quicktime = new ArrayList<Object>();
    @JsonProperty("youtube")
    private List<Youtube> youtube = new ArrayList<Youtube>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The quicktime
     */
    @JsonProperty("quicktime")
    public List<Object> getQuicktime() {
        return quicktime;
    }

    /**
     * 
     * @param quicktime
     *     The quicktime
     */
    @JsonProperty("quicktime")
    public void setQuicktime(List<Object> quicktime) {
        this.quicktime = quicktime;
    }

    /**
     * 
     * @return
     *     The youtube
     */
    @JsonProperty("youtube")
    public List<Youtube> getYoutube() {
        return youtube;
    }

    /**
     * 
     * @param youtube
     *     The youtube
     */
    @JsonProperty("youtube")
    public void setYoutube(List<Youtube> youtube) {
        this.youtube = youtube;
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
