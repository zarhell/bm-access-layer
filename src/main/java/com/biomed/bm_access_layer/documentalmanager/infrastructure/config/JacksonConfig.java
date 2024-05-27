package com.biomed.bm_access_layer.documentalmanager.infrastructure.config;

import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.CustomLocalDateTimeSerializer;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.DocumentalAreaConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.DocumentalDatesConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.DocumentalUserConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(
            CustomLocalDateTimeSerializer customLocalDateTimeSerializer,
            DocumentalAreaConverter documentalAreaConverter,
            DocumentalDatesConverter documentalDatesConverter,
            DocumentalUserConverter documentalUserConverter) {

        ObjectMapper mapper = new ObjectMapper();

        // Registrar el módulo para la serialización/deserialización de Java Time
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, customLocalDateTimeSerializer);
        mapper.registerModule(javaTimeModule);

        // Crear un módulo simple para los convertidores personalizados
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(DocumentalAreaConverter.class, documentalAreaConverter);
        simpleModule.addSerializer(DocumentalDatesConverter.class, documentalDatesConverter);
        simpleModule.addSerializer(DocumentalUserConverter.class, documentalUserConverter);
        mapper.registerModule(simpleModule);

        return mapper;
    }
}