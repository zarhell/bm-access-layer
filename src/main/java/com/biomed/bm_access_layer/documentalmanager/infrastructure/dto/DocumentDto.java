package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumCorrespondenceType;
import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumDocumentPriority;
import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumDocumentType;
import com.biomed.bm_access_layer.documentalmanager.domain.model.DocumentalLifeCycleDates;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

/**
 * Holds information related to a document.
 * This class is used for transferring document data between different layers or components of the system.
 */
@Data
@JsonNaming(SnakeCaseStrategy.class)
public class DocumentDto {

    private EnumDocumentType documentType;
    private DocumentAreaDto documentArea;
    private DocumentalUserDto documentalUser;
    private EnumCorrespondenceType correspondenceType;
    private EnumDocumentPriority documentPriority;
    private String printedTag;
    private DocumentalLifeCycleDates documentalDates;
    private String documentName;
    private String observations;
}

