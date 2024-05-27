package com.biomed.bm_access_layer.documentalmanager.application.usecase;

import com.biomed.bm_access_layer.documentalmanager.domain.model.CoreSavedDocument;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentFetchDtoRS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

/**
 * The FetchStrategy interface defines the contract for fetching a document
 * based on the given existing document information.
 * Implementations of this interface provide the specific logic for retrieving
 * the document from a particular storage or service.
 *
 * The main method in this interface is retrieveDocument, which takes an
 * Optional of CoreSavedDocument as a parameter
 * and returns a DocumentFetchDtoRS object containing the fetched document
 * details. The existingDocument parameter
 * provides the necessary information to locate and retrieve the document.
 *
 * Implementations of this interface may handle different storage systems, such
 * as local file systems or cloud storage providers,
 * and utilize different mechanisms to fetch the document, such as direct file
 * access or API calls to the storage service.
 */
public interface FetchStrategy {
    /**
     * Retrieves a document based on the given existing document information.
     *
     * @param existingDocument An Optional containing the existing document information.
     * @return A DocumentFetchDtoRS object containing the fetched document details.
     * @throws FileNotFoundException If the document is not found.
     * @throws IOException           If an IO error occurs during the retrieval process.
     */
    DocumentFetchDtoRS retrieveDocument(Optional<CoreSavedDocument> existingDocument)
            throws FileNotFoundException, IOException;
}
