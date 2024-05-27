package com.biomed.bm_access_layer.documentalmanager.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

/**
 * The DocumentalSerialDto class represents a documental serial with its name, subserial, and documental types.

 * The class includes three properties: name, subserial, and documentalTypes, representing the name of the serial,
 * the subserial associated with the serial, and the documental types associated with the serial.
 */
@Data
@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentalSerialDto {
    String name;
    String subSerial;
    String documentalTypes;
}
