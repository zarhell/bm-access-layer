package com.biomed.bm_access_layer.documentalmanager.infrastructure.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.log4j.Log4j2;

/**
 * The WebConfiguration class configures the web application.
 */
@Log4j2
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Configures Cross-Origin Resource Sharing (CORS) for all endpoints.
     *
     * @param registry The CorsRegistry object.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
        log.info("WebConfiguration | addCorsMappings | CORS configuration completed");
    }
}