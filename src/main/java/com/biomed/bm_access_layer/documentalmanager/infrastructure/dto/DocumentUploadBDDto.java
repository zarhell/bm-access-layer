package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class DocumentUploadBDDto {
    @JsonProperty("document_properties")
    private DocumentDto documentProperties;

    @JsonIgnore
    private Map<String, Object> extraProps = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getExtraProps() {
        return this.extraProps;
    }
    @JsonAnySetter
    public void setExtraProps(String name, Object value) {
        this.extraProps.put(name, value);
    }

}
