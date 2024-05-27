package com.biomed.bm_access_layer.documentalmanager.application.usecase;

import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentFetchDtoRS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * The DownloadFileService interface provides methods to fetch and download a
 * document file.
 * It defines the contract for classes that implement the functionality to fetch
 * documents from a storage location.
 */
public interface DownloadFileService {

    /**
     * Fetches the document associated with the given storage ID and user lend code.
     *
     * @param storageId   The ID of the document storage location.
     * @param userLendCode The user lend code associated with the document.
     * @return A DocumentFetchDtoRS object containing the fetched document details.
     * @throws FileNotFoundException If the document is not found in the storage location.
     * @throws IOException           If an IO error occurs during the fetch process.
     */
    public DocumentFetchDtoRS fetchDocument(UUID storageId, String userLendCode)
            throws FileNotFoundException, IOException;
}