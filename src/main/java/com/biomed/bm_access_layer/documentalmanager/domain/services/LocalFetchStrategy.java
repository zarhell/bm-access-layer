package com.biomed.bm_access_layer.documentalmanager.domain.services;

import com.biomed.bm_access_layer.documentalmanager.application.usecase.FetchStrategy;
import com.biomed.bm_access_layer.documentalmanager.domain.model.CoreSavedDocument;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.CustomBase64Utils;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentFetchDtoRS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;

/**
 * The LocalFetchStrategy class is an implementation of the FetchStrategy
 * interface that retrieves a document
 * from the local file system.
 *
 * The retrieveDocument method in this class takes an Optional of
 * CoreSavedDocument as a parameter,
 * representing the existing document information, and returns a
 * DocumentFetchDtoRS object containing
 * the fetched document details.
 *
 * If the existingDocument is not present or the document file is not found in
 * the specified path,
 * the method throws a FileNotFoundException.
 *
 * The implementation reads the document file from the local file system,
 * encodes it to Base64 using the Utils class,
 * and creates a DocumentFetchDtoRS object with the Base64-encoded content and
 * the document name.
 */
@Log4j2
@Component("LOCAL")
public class LocalFetchStrategy implements FetchStrategy {

    /**
     * Retrieves a document from the local file system based on the given existing document information.
     *
     * @param existingDocument An Optional containing the existing document information.
     * @return A DocumentFetchDtoRS object containing the fetched document details.
     * @throws FileNotFoundException If the document file is not found.
     * @throws IOException           If an IO error occurs during the retrieval process.
     */
    @Override
    public DocumentFetchDtoRS retrieveDocument(Optional<CoreSavedDocument> existingDocument)
            throws FileNotFoundException, IOException {

        if (!existingDocument.isPresent()) {
            throw new FileNotFoundException("File not found");
        }
        String documentPath = existingDocument.get().getDocumentPath();

        File file = new File(documentPath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + documentPath);
        }

        DocumentFetchDtoRS response = getDocumentResponse(file);

        log.info("Retrieved document from local file system: {}", file.getAbsolutePath());
        return response;
    }

    private DocumentFetchDtoRS getDocumentResponse(File file) throws FileNotFoundException, IOException {
        FileInputStream inputStream = new FileInputStream(file);
        String fileOutB64 = CustomBase64Utils.encodeToBase64(inputStream);

        DocumentFetchDtoRS response = new DocumentFetchDtoRS();
        response.setInputStream(fileOutB64);
        response.setDocumentName(file.getName());
        return response;
    }
}
