package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.biomed.bm_access_layer.documental_manager.domain.model.DocumentalUserDto;
import com.biomed.bm_access_layer.documental_manager.infrastructure.services.Utils;
import org.springframework.stereotype.Component;

@Component
@Converter(autoApply = true)
public class DocumentalUserConverter implements AttributeConverter<DocumentalUserDto, String> {

    @Override
    public String convertToDatabaseColumn(DocumentalUserDto documentalUser) {
        return Utils.serializeObject(documentalUser);
    }

    @Override
    public DocumentalUserDto convertToEntityAttribute(String jsonUser) {
        return Utils.deserializeObject(jsonUser, DocumentalUserDto.class);
    }
}