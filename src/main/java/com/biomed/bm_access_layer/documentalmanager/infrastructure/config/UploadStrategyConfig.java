package com.biomed.bm_access_layer.documentalmanager.infrastructure.config;


import com.biomed.bm_access_layer.documentalmanager.application.usecase.UploadFileService;
import com.biomed.bm_access_layer.documentalmanager.application.usecase.UploadStrategy;
import com.biomed.bm_access_layer.documentalmanager.domain.common.StrategyStorareConstants;
import com.biomed.bm_access_layer.documentalmanager.domain.repository.UploadFileRepository;
import com.biomed.bm_access_layer.documentalmanager.domain.services.CloudStorageUploadStrategy;
import com.biomed.bm_access_layer.documentalmanager.domain.services.LocalUploadStrategy;
import com.biomed.bm_access_layer.documentalmanager.domain.services.UploadFileServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.log4j.Log4j2;

/**
 * The UploadStrategyConfig class is a configuration class that provides the upload strategy configuration
 * for file uploading.
 */
@Log4j2
@NoArgsConstructor
@Configuration
public class UploadStrategyConfig {

    private UploadFileRepository repository;
    private BlobUuidExtractor uuidExtractor;

    @Value("${upload.strategy}")
    private String uploadStrategy;

    public UploadStrategyConfig(UploadFileRepository repository, BlobUuidExtractor uuidExtractor, String uploadStrategy) {
        this.repository = repository;
        this.uuidExtractor = uuidExtractor;
        this.uploadStrategy = uploadStrategy;
    }

    /**
     * Creates the upload strategy based on the configured upload strategy value.
     *
     * @return The configured upload strategy.
     * @throws IllegalArgumentException If an invalid upload strategy is provided.
     */
    @Bean
    public UploadStrategy uploadStrategy() {
        log.info("Creating upload strategy: {}", uploadStrategy);
        try {
            if (uploadStrategy.equals(StrategyStorareConstants.LOCAL)) {
                return new LocalUploadStrategy(repository);
            } else if (uploadStrategy.equals(StrategyStorareConstants.GCP_BUCKET_NAME)) {
                return new CloudStorageUploadStrategy(repository, uuidExtractor);
            } else {
                throw new IllegalArgumentException("Invalid upload strategy: " + uploadStrategy);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating upload strategy: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    /**
     * Creates the upload file service with the configured upload strategy.
     *
     * @param uploadStrategy The upload strategy to be used by the upload file service.
     * @return The upload file service.
     */
    @Bean
    public UploadFileService uploadFileService(UploadStrategy uploadStrategy) {
        log.info("Creating upload file service");
        return new UploadFileServiceImpl(uploadStrategy);
    }
}