package com.biomed.bm_access_layer.documentalmanager.domain.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import com.biomed.bm_access_layer.documentalmanager.application.usecase.DownloadFileService;
import com.biomed.bm_access_layer.documentalmanager.application.usecase.FetchStrategy;
import com.biomed.bm_access_layer.documentalmanager.domain.model.CoreSavedDocument;
import com.biomed.bm_access_layer.documentalmanager.domain.repository.UploadFileRepository;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentFetchDtoRS;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


/**
 * The DownloadFileServiceImpl class is an implementation of the
 * DownloadFileService interface.
 * It provides the functionality to fetch and download a document file using a
 * specified fetch strategy.
 *
 * The main method in this class is fetchDocument, which takes a storage ID and
 * a user lend code as parameters.
 * It retrieves the document with the given storage ID from the repository and
 * uses the fetch strategy to retrieve
 * the document details. If the document is found and accessible, it returns a
 * DocumentFetchDtoRS object containing
 * the fetched document details. Otherwise, it throws a FileNotFoundException or
 * an IOException indicating the failure
 * to fetch the document.
 */
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Service
public class DownloadFileServiceImpl implements DownloadFileService {

    private FetchStrategy fetchStrategy;
    private UploadFileRepository repository;

    /**
     * Fetches the document associated with the given storage ID and user lend code.
     *
     * @param storageId    The ID of the document storage location.
     * @param userLendCode The user lend code associated with the document.
     * @return A DocumentFetchDtoRS object containing the fetched document details.
     * @throws FileNotFoundException If the document is not found in the storage location.
     * @throws IOException           If an IO error occurs during the fetch process.
     */
    @Override
    public DocumentFetchDtoRS fetchDocument(UUID storageId, String userLendCode)
            throws FileNotFoundException, IOException {
        log.info("DownloadFileServiceImpl | fetchDocument | Fetching document with storageId: {}, userLendCode: {}", storageId, userLendCode);
        Optional<CoreSavedDocument> existingDocument = repository.findById(storageId);
        if (!existingDocument.isPresent()) {
            log.error("DownloadFileServiceImpl | fetchDocument | Document with storageId: {} not found", storageId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested document not found");
        }
        DocumentFetchDtoRS response = fetchStrategy.retrieveDocument(existingDocument);
        updateUserLendCode(userLendCode, existingDocument.get());
        log.info("DownloadFileServiceImpl | fetchDocument | Document fetched successfully");
        return response;
    }

    private void updateUserLendCode(String userLendCode, CoreSavedDocument existingDocument) {
        existingDocument.getDocumentalUser().setUserLendCode(userLendCode);
    }
}