package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;


/**
 * Represents a document area related to a Document.
 * It contains information about the original area code, destination area code, and area name.
 * This DTO is typically used for transferring document area-related data between different layers or components of the system.
 */
@Data
@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentAreaDto {

    private String originalAreaCode;
    private String destinationAreaCode;
    private String areaName;
}
