package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;


import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.Utils;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentalUserDto;
import jakarta.persistence.AttributeConverter;
import lombok.extern.log4j.Log4j2;

/**
 * The {@code DocumentalUserConverter} class is an attribute converter used in JPA for converting
 * {@link DocumentalUserDto} objects to and from their JSON representation in the database.
 * This converter facilitates the storage and retrieval of DocumentalUserDto objects in a persistent entity.
 */
@Log4j2
public class DocumentalUserConverter implements AttributeConverter<DocumentalUserDto, String> {

    /**
     * Converts a {@link DocumentalUserDto} object to its JSON string representation for storing in the database.
     *
     * @param documentalUser  the DocumentalUserDto object to be converted
     * @return the JSON string representation of the DocumentalUserDto object
     */
    @Override
    public String convertToDatabaseColumn(DocumentalUserDto documentalUser) {
        log.info("DocumentalUserDtoConverter | convertToDatabaseColumn | Converting DocumentalUserDto to JSON string");
        return Utils.serializeObject(documentalUser);
    }

    /**
     * Converts a JSON string representation of a DocumentalUserDto object to a {@link DocumentalUserDto} object
     * for retrieval from the database.
     *
     * @param jsonUser  the JSON string representation of the DocumentalUserDto object
     * @return the deserialized DocumentalUserDto object
     */
    @Override
    public DocumentalUserDto convertToEntityAttribute(String jsonUser) {
        log.info("DocumentalUserDtoConverter | convertToEntityAttribute | Converting JSON string to DocumentalUserDto");
        return Utils.deserializeObject(jsonUser, DocumentalUserDto.class);
    }

}