package com.biomed.bm_access_layer.documentalmanager.infrastructure.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;


@Log4j2
@Component
public class GcpCredentialsServiceHelper {

    @Value("${gcp.key.path}")
    private String gcpKeyPath;

    private Storage storage;

    public void initialize() {
        try {
            log.info("GcpCredentialsServiceHelper | initialize | Setting GCP Credentials");
            String keyFilePath = resolveKeyFilePath();
            final Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(keyFilePath));

            storage = StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();

            log.debug("GcpCredentialsServiceHelper | initialize | Storage details: " + storage.toString());
        } catch (final FileNotFoundException e) {
            log.error("GcpCredentialsServiceHelper | initialize | GCP key file not found.", e);
        } catch (final IOException e) {
            log.error("GcpCredentialsServiceHelper | initialize | Error loading GCP credentials.", e);
        }
    }

    public Storage getCredentialsStorage(final String bucketName) {
        log.debug("GcpCredentialsServiceHelper | getCredentialsStorage | Retrieving the storage instance");
        return storage;
    }

    private String resolveKeyFilePath() {
        Path basePath = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path keyFilePath = Paths.get(basePath.toString(), gcpKeyPath);
        return keyFilePath.toString();
    }

}
