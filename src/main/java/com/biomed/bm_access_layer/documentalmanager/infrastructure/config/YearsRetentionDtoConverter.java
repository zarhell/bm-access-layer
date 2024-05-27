package com.biomed.bm_access_layer.documentalmanager.infrastructure.config;


import javax.persistence.AttributeConverter;

import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.YearsRetentionDto;
import lombok.extern.log4j.Log4j2;

/**
 * The YearsRetentionDtoConverter class is responsible for converting a
 * YearsRetentionDto object
 * to its string representation for storing in the database and vice versa.
 */
@Log4j2
public class YearsRetentionDtoConverter implements AttributeConverter<YearsRetentionDto, String> {

    /**
     * Converts a YearsRetentionDto object to its string representation for storing
     * in the database.
     *
     * @param yearsRetention The YearsRetentionDto object to be converted.
     * @return The string representation of the YearsRetentionDto object.
     */
    @Override
    public String convertToDatabaseColumn(YearsRetentionDto yearsRetention) {
        log.info(
                "YearsRetentionDtoConverter | convertToDatabaseColumn | Converting YearsRetentionDto to database column representation");
        return Utils.serializeObject(yearsRetention);
    }

    /**
     * Converts a string representation of a YearsRetentionDto object retrieved from
     * the database
     * back to the YearsRetentionDto type.
     *
     * @param jsonConfig The string representation of the YearsRetentionDto object.
     * @return The deserialized YearsRetentionDto object.
     */
    @Override
    public YearsRetentionDto convertToEntityAttribute(String jsonConfig) {
        log.info(
                "YearsRetentionDtoConverter | convertToEntityAttribute | Converting database column to YearsRetentionDto");
        return Utils.deserializeObject(jsonConfig, YearsRetentionDto.class);
    }
}