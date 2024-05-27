package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;


import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentAreaDto;

import javax.persistence.AttributeConverter;

public class DocumentalAreaConverter implements AttributeConverter<DocumentAreaDto, String>{

    @Override
    public String convertToDatabaseColumn(DocumentAreaDto documentArea) {
        return Utils.serializeObject(documentArea);
    }

    @Override
    public DocumentAreaDto convertToEntityAttribute(String jsonArea) {
        return Utils.deserializeObject(jsonArea, DocumentAreaDto.class);
    }

}