package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;

import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.Utils;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.SpecificationFeaturesDto;
import lombok.extern.log4j.Log4j2;

import javax.persistence.AttributeConverter;
/**
 * The SpecificationFeaturesConverter class is responsible for converting a
 * SpecificationFeaturesDto object
 * to its string representation for storing in the database and vice versa.
 *
 */
@Log4j2
public class SpecificationFeaturesConverter implements AttributeConverter<SpecificationFeaturesDto, String> {
    /**
     * Converts a SpecificationFeaturesDto object to its string representation for
     * storing in the database.
     *
     * @param specificationFeatures The SpecificationFeaturesDto object to be
     *                              converted.
     * @return The string representation of the SpecificationFeaturesDto object.
     */
    @Override
    public String convertToDatabaseColumn(SpecificationFeaturesDto specificationFeatures) {
        log.info(
                "SpecificationFeaturesConverter | convertToDatabaseColumn | Converting SpecificationFeaturesDto to database column representation");
        return Utils.serializeObject(specificationFeatures);
    }

    /**
     * Converts a string representation of a SpecificationFeaturesDto object
     * retrieved from the database
     * back to the SpecificationFeaturesDto type.
     *
     * @param jsonConfig The string representation of the SpecificationFeaturesDto
     *                   object.
     * @return The deserialized SpecificationFeaturesDto object.
     */
    @Override
    public SpecificationFeaturesDto convertToEntityAttribute(String jsonConfig) {
        log.info(
                "SpecificationFeaturesConverter | convertToEntityAttribute | Converting database column to SpecificationFeaturesDto");
        return Utils.deserializeObject(jsonConfig, SpecificationFeaturesDto.class);
    }
}