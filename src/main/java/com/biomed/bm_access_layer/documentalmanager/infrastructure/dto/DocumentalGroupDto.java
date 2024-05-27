package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

/**
 * The DocumentalGroupDto class represents a documental group with its code and
 * name.
 *
 * The class includes two properties: code and name, representing the code and
 * name of the documental group.
 */
@Data
@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentalGroupDto {
    private String code;
    private String name;
}
