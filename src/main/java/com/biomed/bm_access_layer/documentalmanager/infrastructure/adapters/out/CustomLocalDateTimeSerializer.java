package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import static com.biomed.bm_access_layer.documentalmanager.domain.common.Constans.DATE_PATTERN;

@Component
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {


    private final DateTimeFormatter formatter;

    public CustomLocalDateTimeSerializer() {
        this.formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    }

    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider sp)
            throws IOException, JsonProcessingException {
        String formattedDateTime = dateTime.format(formatter);
        generator.writeString(formattedDateTime);
    }
}