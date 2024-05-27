package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

/**
 * The OrganitationDto class represents an organization with its relevant information
 *  that contains the following properties:
 * - productiveName: The name of the productive organization.
 * - dependencyName: The name of the dependent organization.
 * - coreDocumentalGroup: The documental group associated with the organization.
 */
@Data
@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationDto {

    private String productiveName;
    private String dependencyName;
    private DocumentalGroupDto coreDocumentalGroup;

}
