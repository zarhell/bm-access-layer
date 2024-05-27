package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumDocumentType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class DocumentDto {

    private static final long serialVersionUID = 1L;

    private EnumDocumentType documentType;

    private DocumentAreaDto documentArea;

    private DocumentalUserDto documentalUser;

    private String correspondenceType;

    private String documentPriority;

    private String printedTag;

    private DocumentalLifeCycleDates documentalDates;

    private String documentName;

    private String observations;
}
