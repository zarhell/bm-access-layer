package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.biomed.bm_access_layer.documental_manager.domain.model.DocumentalLifeCycleDates;
import com.biomed.bm_access_layer.documental_manager.infrastructure.services.Utils;
import org.springframework.stereotype.Component;

@Component
@Converter(autoApply = true)
public class DocumentalDatesConverter implements AttributeConverter<DocumentalLifeCycleDates, String> {

    @Override
    public String convertToDatabaseColumn(DocumentalLifeCycleDates documentDates) {
        return Utils.serializeObject(documentDates);
    }

    @Override
    public DocumentalLifeCycleDates convertToEntityAttribute(String jsonDates) {
        return Utils.deserializeObject(jsonDates, DocumentalLifeCycleDates.class);
    }
}