package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
/**
 * Represents a documental user assigned for an Document.
 * It contains information about the user's origin code, destiny code, and lend code.
 */
@Data
@JsonNaming(SnakeCaseStrategy.class)
public class DocumentalUserDto {

    private String userOriginCode;
    private String userDestinyCode;
    private String userLendCode;
}
