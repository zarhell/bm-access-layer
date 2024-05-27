package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import lombok.extern.log4j.Log4j2;

/**
 * The {@code CustomLocalDateTimeSerializer} class is a custom Jackson serializer for LocalDateTime objects.
 * It serializes LocalDateTime objects to a specific format, such as "yyyy-MM-dd".
 * This serializer is used to define how LocalDateTime objects should be converted to JSON.
 */
@Log4j2
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime>{

    /**
     * Serializes a LocalDateTime object to a specific format and writes it to the JSON generator.
     *
     * @param dateTime   the LocalDateTime object to be serialized
     * @param generator  the JSON generator to write the serialized string to
     * @param sp         the serializer provider
     * @throws IOException              if an I/O error occurs while writing to the generator
     * @throws JsonProcessingException  if a JSON processing error occurs during serialization
     */
    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider sp)
            throws IOException, JsonProcessingException {
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        log.info("CustomLocalDateTimeSerializer | serialize | Serializing LocalDateTime");
        generator.writeString( formattedDateTime);
    }

}