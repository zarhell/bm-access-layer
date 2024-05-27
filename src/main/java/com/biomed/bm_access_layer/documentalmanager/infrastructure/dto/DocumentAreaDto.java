package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
public class DocumentAreaDto {
    private static final long serialVersionUID = 1L;

    private String originalAreaCode;

    private String destinationAreaCode;

    private String areaName;

}
