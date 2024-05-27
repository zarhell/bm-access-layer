package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumCorrespondenceType;
import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumDocumentType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * The DocumentSearchDtoRS class represents the response for a document search
 * operation.
 * It is used to encapsulate the search results, including various
 * document-related information.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record DocumentSearchDtoRS(LocalDateTime documentalCreateDate,
                                  String documentalSerial,
                                  EnumDocumentType documentType,
                                  DocumentAreaDto documentArea,
                                  DocumentalUserDto userLocation,
                                  EnumCorrespondenceType correspondenceType,
                                  String userName)
        implements Serializable {
    private static final long serialVersionUID = 1L;
}
