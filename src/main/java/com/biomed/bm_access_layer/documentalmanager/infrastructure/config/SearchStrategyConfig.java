package com.biomed.bm_access_layer.documentalmanager.infrastructure.config;

import com.biomed.bm_access_layer.documentalmanager.application.usecase.DownloadFileService;
import com.biomed.bm_access_layer.documentalmanager.application.usecase.FetchStrategy;
import com.biomed.bm_access_layer.documentalmanager.domain.common.StrategyStorareConstants;
import com.biomed.bm_access_layer.documentalmanager.domain.repository.UploadFileRepository;
import com.biomed.bm_access_layer.documentalmanager.domain.services.DownloadFileServiceImpl;
import com.biomed.bm_access_layer.documentalmanager.domain.services.GoogleCloudStorageFetchStrategy;
import com.biomed.bm_access_layer.documentalmanager.domain.services.LocalFetchStrategy;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;

/**
 * The SearchStrategyConfig class is a configuration class that provides the
 * search strategy configuration for file fetching and downloading.
 */
@Log4j2
@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class SearchStrategyConfig {

    @Value("${fetch.strategy}")
    private String fetchStrategy;


    @Bean
    public FetchStrategy fetchStrategy() {
        log.info("Fetching the strategy for file fetching");
        try {
            if (fetchStrategy.equals(StrategyStorareConstants.LOCAL)) {
                log.info("Using LocalFetchStrategy for file fetching");
                return new LocalFetchStrategy();
            } else if (fetchStrategy.equals(StrategyStorareConstants.GCP_BUCKET_NAME)) {
                log.info("Using GoogleCloudStorageFetchStrategy for file fetching");
                return new GoogleCloudStorageFetchStrategy();
            } else {
                throw new IllegalArgumentException("Invalid fetch strategy: " + fetchStrategy);
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Fetch strategy is null", e);
        }
    }

    @Bean
    public DownloadFileService downloadFileService(FetchStrategy fetchStrategy, UploadFileRepository repository) {
        log.info("Creating DownloadFileService with the specified fetch strategy and repository");
        return new DownloadFileServiceImpl(fetchStrategy, repository);
    }
}
