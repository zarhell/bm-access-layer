package com.biomed.bm_access_layer.documentalmanager.infrastructure.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import lombok.extern.log4j.Log4j2;

/**
 * The GCPauthExplicit class provides a method to authenticate with Google Cloud
 * Platform (GCP) using explicit credentials.
 * It uses the GCP Key JSON file for authentication.
 *
 * The GCPauthExplicit class provides a method to authenticate with Google Cloud
 * Platform (GCP) using explicit credentials.
 * It uses the GCP Key JSON file for authentication.
 *
 */
@Log4j2
public class GCPauthExplicit {

    private static final String GCP_KEY_JSON = "gcp-key.json";

    /**
     * Authenticates with GCP using explicit credentials from the GCP Key JSON file.
     *
     * @param jsonPath The path to the GCP Key JSON file.
     * @throws IOException If an error occurs while reading the JSON file or
     *                     interacting with GCP Storage.
     */
    static void authExplicit(String jsonPath) throws IOException {
        log.info("Authenticating with GCP using explicit credentials.");

        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(GCP_KEY_JSON));
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

            Page<Bucket> buckets = storage.list();
            for (Bucket bucket : buckets.iterateAll()) {
                log.info("Buckets:" + bucket.toString());
            }
        } catch (Exception e) {
            log.error("Error occurred during GCP authentication: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}