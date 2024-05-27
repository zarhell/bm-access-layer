package com.biomed.bm_access_layer.documentalmanager.domain.services;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.biomed.bm_access_layer.documentalmanager.application.usecase.UploadFileService;
import com.biomed.bm_access_layer.documentalmanager.application.usecase.UploadStrategy;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.in.DocumentUploadBD;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

/**
 * The UploadFileServiceImpl class is responsible for handling file uploads and processing them.
 * It implements the UploadFileService interface.
 */
@Log4j2
@Service
public final class UploadFileServiceImpl implements UploadFileService {

    private UploadStrategy uploadStrategy;
    private ExecutorService executorService;

    public UploadFileServiceImpl(UploadStrategy uploadStrategy) {
        this.uploadStrategy = uploadStrategy;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    /**
     * Uploads files and processes them using the specified upload strategy.
     *
     * @param documentalSerial The documental serial.
     * @param file             The file to upload.
     * @param requestBody      The request body.
     * @return The updated DocumentUploadBD.
     * @throws FileNotFoundException If the file is not found.
     * @throws IOException           If an I/O error occurs.
     */
    @Override
    public DocumentUploadBD uploadFilesRS(String documentalSerial, MultipartFile file, String requestBody) throws FileNotFoundException, IOException {
        log.info("DocumentUploadBD | uploadFilesRS | Start upload process");
        Future<DocumentUploadBD> future = executorService.submit(() -> {
            return uploadStrategy.uploadFilesRS(documentalSerial, file, requestBody);
        });
        DocumentUploadBD result;
        try {
            result = future.get();
            log.info("UploadFileServiceImpl | uploadFilesRS | Upload process completed");
        } catch (Exception e) {
            log.error("DocumentUploadBD | uploadFilesRS | Error to execute the upload file task", e);
            throw new RuntimeException("DocumentUploadBD | uploadFilesRS | Error to load the file", e);
        }

        return result;
    }
}