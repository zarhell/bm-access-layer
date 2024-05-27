package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The DocumentFetchDtoRS class represents the response for a document fetch
 * operation.
 * It is used to encapsulate the fetched document's input stream and its name.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class DocumentFetchDtoRS {

    private String inputStream;
    private String documentName;
}
