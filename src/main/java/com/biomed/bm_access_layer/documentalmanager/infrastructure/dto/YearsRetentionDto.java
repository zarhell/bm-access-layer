package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

/**
 * The YearsRetentionDto class represents the number of years for file
 * management and file centralization in a retention policy
 * that contains the following properties:
 * - fileManagement: The number of years for file management.
 * - fileCentral: The number of years for file centralization.
 */
@Data
@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class YearsRetentionDto{
    Integer fileManagement;
    Integer fileCentral;
}
