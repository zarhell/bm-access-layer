package com.biomed.bm_access_layer.documentalmanager.domain.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringJoiner;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;

@Log4j2
@Service
public class GcpCredentialsService {

    /**
     * protected  constructor to enforce singleton behavior.
     */
    protected  GcpCredentialsService() {
    }

    /**
     * The singleton instance of GcpCredentialsService.
     */
    private static GcpCredentialsService instance;
    private static Storage storage;

    /**
     * The path to the GCP key JSON file.
     */
    @Value("${gcp.key.path}")
    private static final String GCP_KEY_JSON = new StringJoiner(File.separator)
            .add("src")
            .add("main")
            .add("resources")
            .add("gcp-key.json")
            .toString();



    /**
     * Initializes the GCP credentials and sets up the storage instance.
     * This method is called internally during the initialization of the singleton
     * instance.
     */
    public static void initialize() {
        try {
            log.info("GcpCredentialsService | initialize | Setting GCP Credentials");
            final Credentials credentials = GoogleCredentials.fromStream(
                    new FileInputStream(GCP_KEY_JSON));

            storage = StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();

            log.debug("GcpCredentialsService | initialize | Storage details: " + storage.toString());
        } catch (final FileNotFoundException e) {
            log.error("GcpCredentialsService | initialize | GCP key file not found.", e);
        } catch (final IOException e) {
            log.error("GcpCredentialsService | initialize | Error loading GCP credentials.", e);
        }
    }

    /**
     * Retrieves the storage instance with the configured GCP credentials.
     *
     * @param bucketName The name of the Google Cloud Storage bucket.
     * @return The storage instance configured with the GCP credentials.
     */
    public Storage getCredentialsStorage(final String bucketName) {
        log.debug("GcpCredentialsService | getCredentialsStorage | Retrieving the storage instance");
        return storage;
    }

    /**
     * Retrieves the singleton instance of GcpCredentialsService.
     * Initializes the instance if it is null.
     *
     * @return The singleton instance of GcpCredentialsService.
     */
    public static GcpCredentialsService getInstance() {
        log.debug("GcpCredentialsService | getInstance | Retrieving the singleton instance");
        if (instance == null) {
            instance = new GcpCredentialsService();
            GcpCredentialsService.initialize();
        }
        return instance;
    }
}
