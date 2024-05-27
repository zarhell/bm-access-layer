package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;

import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.Utils;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentAreaDto;
import jakarta.persistence.AttributeConverter;
import lombok.extern.log4j.Log4j2;

/**
 * The {@code DocumentalAreaConverter} class is an attribute converter used in JPA for converting
 * {@link DocumentAreaDto} objects to and from their JSON representation in the database.
 * This converter facilitates the storage and retrieval of DocumentAreaDto objects in a persistent entity.
 */
@Log4j2
public class DocumentalAreaConverter implements AttributeConverter<DocumentAreaDto, String> {

    /**
     * Converts a {@link DocumentAreaDto} object to its JSON string representation for storing in the database.
     *
     * @param documentArea  the DocumentAreaDto object to be converted
     * @return the JSON string representation of the DocumentAreaDto object
     */
    @Override
    public String convertToDatabaseColumn(DocumentAreaDto documentArea) {
        log.info("DocumentalAreaConverter | convertToDatabaseColumn | Converting DocumentAreaDto to JSON string");
        return Utils.serializeObject(documentArea);
    }

    /**
     * Converts a JSON string representation of a DocumentAreaDto object to a {@link DocumentAreaDto} object
     * for retrieval from the database.
     *
     * @param jsonArea  the JSON string representation of the DocumentAreaDto object
     * @return the deserialized DocumentAreaDto object
     */
    @Override
    public DocumentAreaDto convertToEntityAttribute(String jsonArea) {
        log.info("DocumentalAreaConverter | convertToEntityAttribute | Converting JSON string to DocumentAreaDto");
        return Utils.deserializeObject(jsonArea, DocumentAreaDto.class);
    }
}
