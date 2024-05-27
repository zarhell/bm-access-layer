package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;

import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.Utils;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.OrganizationDto;
import lombok.extern.log4j.Log4j2;

import javax.persistence.AttributeConverter;

/**
 * The OrganitationConverter class is responsible for converting an
 * OrganitationDto object to its
 * string representation for storing in the database and vice versa.
 */
@Log4j2
public class OrganitationConverter implements AttributeConverter<OrganizationDto, String> {

    /**
     * Converts an OrganitationDto object to its string representation for storing
     * in the database.
     *
     * @param organitation The OrganitationDto object to be converted.
     * @return The string representation of the OrganitationDto object.
     */
    @Override
    public String convertToDatabaseColumn(OrganizationDto organitation) {
        log.info(
                "OrganitationConverter | convertToDatabaseColumn | Converting OrganitationDto to database column representation");
        return Utils.serializeObject(organitation);
    }

    /**
     * Converts a string representation of an OrganitationDto object retrieved from
     * the database
     * back to the OrganitationDto type.
     *
     * @param jsonConfig The string representation of the OrganitationDto object.
     * @return The deserialized OrganitationDto object.
     */
    @Override
    public OrganizationDto convertToEntityAttribute(String jsonConfig) {
        log.info("OrganitationConverter | convertToEntityAttribute | Converting database column to OrganitationDto");
        return Utils.deserializeObject(jsonConfig, OrganizationDto.class);
    }
}