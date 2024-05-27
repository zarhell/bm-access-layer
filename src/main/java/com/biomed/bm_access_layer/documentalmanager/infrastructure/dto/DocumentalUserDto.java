package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
@Data
@JsonNaming(SnakeCaseStrategy.class)
public class DocumentalUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userOriginCode;

    private String userDestinyCode;

    private String userLendCode;


}