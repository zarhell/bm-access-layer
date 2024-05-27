package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.in;

import java.util.HashMap;
import java.util.Map;

import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentDto;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Represents a document upload business domain object. It contains the document properties
 * and allows the addition of extra properties using a map. This class is used for handling
 * document uploads in the system.
 */
@Data
public class DocumentUploadBD {

    @JsonProperty("document_properties")
    private DocumentDto documentProperties;
    private Map<String, Object> extraProps = new HashMap<>();

    /**
     * Retrieves the map of extra properties associated with the document upload.
     *
     * @return The map of extra properties.
     */
    @JsonAnyGetter
    public Map<String, Object> getExtraProps() {
        return this.extraProps;
    }
    /**
     * Sets an extra property for the document upload.
     *
     * @param name  The name of the extra property.
     * @param value The value of the extra property.
     */
    @JsonAnySetter
    public void setExtraProps(String name, Object value) {
        this.extraProps.put(name, value);
    }
}