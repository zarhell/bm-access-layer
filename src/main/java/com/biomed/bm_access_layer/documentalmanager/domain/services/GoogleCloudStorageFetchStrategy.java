package com.biomed.bm_access_layer.documentalmanager.domain.services;

import com.biomed.bm_access_layer.documentalmanager.application.usecase.FetchStrategy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.util.Optional;

import com.biomed.bm_access_layer.documentalmanager.domain.common.StrategyStorareConstants;
import com.biomed.bm_access_layer.documentalmanager.domain.model.CoreSavedDocument;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.CustomBase64Utils;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentFetchDtoRS;
import org.springframework.context.annotation.Lazy;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;

import lombok.extern.log4j.Log4j2;

/**
 * The GoogleCloudStorageFetchStrategy class is an implementation of the
 * FetchStrategy interface.
 * It provides the functionality to retrieve a document from Google Cloud
 * Storage.
 *
 * The main method in this class is retrieveDocument, which takes an Optional of
 * CoreSavedDocument as a parameter.
 * It uses the existingDocument to retrieve the corresponding Blob from Google
 * Cloud Storage. Then, it retrieves
 * the InputStream of the Blob, encodes it to Base64, and creates a
 * DocumentFetchDtoRS object containing thedel
 * fetched document details.
 */
@Log4j2
@Lazy
public class GoogleCloudStorageFetchStrategy implements FetchStrategy {

    public GoogleCloudStorageFetchStrategy() {
    }

    /**
     * Retrieves a document from Google Cloud Storage based on the given existing
     * document information.
     *
     * @param existingDocument An Optional containing the existing document
     *                         information.
     * @return A DocumentFetchDtoRS object containing the fetched document details.
     * @throws FileNotFoundException If the document is not found in the Google
     *                               Cloud Storage.
     * @throws IOException           If an IO error occurs during the retrieval
     *                               process.
     */
    @Override
    public DocumentFetchDtoRS retrieveDocument(Optional<CoreSavedDocument> existingDocument)
            throws FileNotFoundException, IOException {

        log.info("GoogleCloudStorageFetchStrategy | retrieveDocument | Retrieving document from Google Cloud Storage");

        Storage storage = getStorageInstance();
        Blob blob = retrieveBlob(existingDocument, storage);
        InputStream inputStream = Channels.newInputStream(blob.reader());
        String fileOutB64 = CustomBase64Utils.encodeToBase64(inputStream);
        DocumentFetchDtoRS response = createDocumentFetchDtoRS(blob, fileOutB64);

        log.info("GoogleCloudStorageFetchStrategy | retrieveDocument | Document retrieved successfully");

        return response;
    }

    /**
     * Retrieves the instance of Google Cloud Storage for the specified bucket name.
     *
     * @return The Storage instance for Google Cloud Storage.
     */
    private Storage getStorageInstance() {
        GcpCredentialsService gcpCredentials = GcpCredentialsService.getInstance();
        return gcpCredentials.getCredentialsStorage(StrategyStorareConstants.GCP_BUCKET_NAME);
    }

    /**
     * Retrieves the Blob from Google Cloud Storage based on the existing document
     * information.
     *
     * @param existingDocument An Optional containing the existing document
     *                         information.
     * @param storage          The Storage instance for Google Cloud Storage.
     * @return The retrieved Blob from Google Cloud Storage.
     */
    protected Blob retrieveBlob(Optional<CoreSavedDocument> existingDocument, Storage storage) {
        BlobId blobId = BlobId.of(StrategyStorareConstants.GCP_BUCKET_NAME, existingDocument.get().getDocumentName());
        return storage.get(blobId);
    }


    /**
     * Creates a DocumentFetchDtoRS object based on the retrieved Blob and
     * Base64-encoded string.
     *
     * @param blob       The retrieved Blob from Google Cloud Storage.
     * @param fileOutB64 The Base64-encoded string representing the document file.
     * @return A DocumentFetchDtoRS object containing the fetched document details.
     */
    protected DocumentFetchDtoRS createDocumentFetchDtoRS(Blob blob, String fileOutB64) {
        DocumentFetchDtoRS response = new DocumentFetchDtoRS();
        response.setInputStream(fileOutB64);
        response.setDocumentName(blob.getBlobId().getName());
        return response;
    }
}
