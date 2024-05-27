package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;



import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
public class DocumentFetchDtoRS {

    private static final long serialVersionUID = 1L;

    private String inputStream;

    private String documentName;
}
