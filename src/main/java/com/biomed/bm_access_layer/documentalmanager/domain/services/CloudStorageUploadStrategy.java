package com.biomed.bm_access_layer.documentalmanager.domain.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import com.biomed.bm_access_layer.documentalmanager.application.usecase.UploadStrategy;
import com.biomed.bm_access_layer.documentalmanager.domain.common.StrategyStorareConstants;
import com.biomed.bm_access_layer.documentalmanager.domain.model.CoreSavedDocument;
import com.biomed.bm_access_layer.documentalmanager.domain.repository.UploadFileRepository;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.in.DocumentUploadBD;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.BlobUuidExtractor;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.extern.log4j.Log4j2;

/**
 * The CloudStorageUploadStrategy class implements the UploadStrategy interface
 * and provides a strategy for uploading files to cloud storage.
 * It uses Google Cloud Storage as the storage provider.
 */
@Log4j2
@Lazy
public class CloudStorageUploadStrategy implements UploadStrategy {

    private static final String DOCUMENTAL_STORAGE_ID = "documental_storage_id";
    private static final String DOCUMENTAL_SERIAL = "documental_serial";

    private UploadFileRepository repository;
    private BlobUuidExtractor uuidExtractor;

    public CloudStorageUploadStrategy(UploadFileRepository repository, BlobUuidExtractor uuidExtractor) {
        this.repository = repository;
        this.uuidExtractor = uuidExtractor;
    }

    /**
     * Uploads files to cloud storage and updates the corresponding document
     * information.
     *
     * @param documentalSerial The documental serial.
     * @param file             The file to upload.
     * @param requestBody      The request body.
     * @return The updated DocumentUploadBD.
     * @throws FileNotFoundException If the file is not found.
     * @throws IOException           If an I/O error occurs.
     */
    @Override
    public DocumentUploadBD uploadFilesRS(String documentalSerial, MultipartFile file, String requestBody)
            throws FileNotFoundException, IOException {
        CoreSavedDocument documentToProcess = getOrCreateDocument(documentalSerial);
        DocumentUploadBD document = deserializeDocumentUploadBD(requestBody);
        copyDocumentProperties(document.getDocumentProperties(), documentToProcess);

        try {
            Storage storage = getStorageInstance();
            BlobId blobId = getBlobIdToSave(file, documentToProcess);
            BlobInfo info = BlobInfo.newBuilder(blobId).build();
            storage.create(info, file.getBytes());

            updateDocumentWithStorageInfo(documentToProcess, blobId, file.getOriginalFilename(), info.getMediaLink());

            repository.save(documentToProcess);

            copyDocumentProperties(documentToProcess, document);
            setDocumentExtraProperties(document, documentalSerial, documentToProcess.getStorageId());

            log.info("CloudStorageUploadStrategy | uploadFilesRS | End upload file. Name: [{}], Content-Type: [{}]",
                    file.getOriginalFilename(), file.getContentType());

            return document;
        } catch (FileNotFoundException e) {
            log.error("CloudStorageUploadStrategy | uploadFilesRS | File not found: {}", e.getMessage());
            throw e;
        } catch (IOException e) {
            log.error("CloudStorageUploadStrategy | uploadFilesRS | I/O error occurred: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("CloudStorageUploadStrategy | uploadFilesRS | An error occurred: {}", e.getMessage());
            throw e;
        }
    }

    private CoreSavedDocument getOrCreateDocument(String documentalSerial) {
        return repository.findByDocumentalSerial(documentalSerial).orElse(new CoreSavedDocument());
    }

    private DocumentUploadBD deserializeDocumentUploadBD(String requestBody) {
        return Utils.deserializeObject(requestBody, DocumentUploadBD.class);
    }

    private void copyDocumentProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    private Storage getStorageInstance() {
        GcpCredentialsService gcpCredentials = GcpCredentialsService.getInstance();
        return gcpCredentials.getCredentialsStorage(StrategyStorareConstants.LOCAL);
    }

    private BlobId getBlobIdToSave(MultipartFile file, CoreSavedDocument documentToProcess) {
        String blobId;
        if (documentToProcess.getStorageId() != null) {
            blobId = "ver " + documentToProcess.getStorageId() + " " + file.getOriginalFilename();
        } else {
            blobId = file.getOriginalFilename();
        }
        return BlobId.of(StrategyStorareConstants.LOCAL, blobId);
    }

    private void updateDocumentWithStorageInfo(CoreSavedDocument documentToProcess, BlobId blobId, String fileName,
                                               String mediaLink) {
        documentToProcess.setStorageId(uuidExtractor.extractUuidFromBlob(blobId.getBucket(), blobId.getName()));
        documentToProcess.setDocumentName(fileName);
        documentToProcess.setDocumentPath(mediaLink);
    }

    private void setDocumentExtraProperties(DocumentUploadBD document, String documentalSerial, UUID storageId) {
        document.setExtraProps(DOCUMENTAL_SERIAL, documentalSerial);
        document.setExtraProps(DOCUMENTAL_STORAGE_ID, storageId);
    }

}
