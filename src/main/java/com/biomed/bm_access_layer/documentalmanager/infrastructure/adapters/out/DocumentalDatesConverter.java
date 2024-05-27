package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;


import com.biomed.bm_access_layer.documentalmanager.domain.model.DocumentalLifeCycleDates;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.Utils;
import jakarta.persistence.AttributeConverter;
import lombok.extern.log4j.Log4j2;


/**
 * The {@code DocumentalDatesConverter} class is an attribute converter used in JPA for converting
 * {@link DocumentalLifeCycleDates} objects to and from their JSON representation in the database.
 * This converter facilitates the storage and retrieval of DocumentalLifeCycleDates objects in a persistent entity.
 */
@Log4j2
public class DocumentalDatesConverter implements AttributeConverter<DocumentalLifeCycleDates, String> {

    /**
     * Converts a {@link DocumentalLifeCycleDates} object to its JSON string representation for storing in the database.
     *
     * @param documentDates  the DocumentalLifeCycleDates object to be converted
     * @return the JSON string representation of the DocumentalLifeCycleDates object
     */
    @Override
    public String convertToDatabaseColumn(DocumentalLifeCycleDates documentDates) {
        log.info("DocumentalLifeCycleDatesConverter | convertToDatabaseColumn | Converting DocumentalLifeCycleDates to JSON string");
        return Utils.serializeObject(documentDates);
    }

    /**
     * Converts a JSON string representation of a DocumentalLifeCycleDates object to a {@link DocumentalLifeCycleDates} object
     * for retrieval from the database.
     *
     * @param jsonDates  the JSON string representation of the DocumentalLifeCycleDates object
     * @return the deserialized DocumentalLifeCycleDates object
     */
    @Override
    public DocumentalLifeCycleDates convertToEntityAttribute(String jsonDates) {
        log.info("DocumentalLifeCycleDatesConverter | convertToEntityAttribute | Converting JSON string to DocumentalLifeCycleDates");
        return Utils.deserializeObject(jsonDates, DocumentalLifeCycleDates.class);
    }
}
