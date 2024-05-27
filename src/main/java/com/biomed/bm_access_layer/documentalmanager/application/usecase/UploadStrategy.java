package com.biomed.bm_access_layer.documentalmanager.application.usecase;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.in.DocumentUploadBD;
import org.springframework.web.multipart.MultipartFile;
/**
 * The UploadStrategy interface defines the contract for uploading files and
 * processing them.
 */
public interface UploadStrategy {
    /**
     * Uploads files and processes them.
     *
     * @param documentalSerial The documental serial.
     * @param file             The file to upload.
     * @param requestBody      The request body.
     * @return The updated DocumentUploadBD.
     * @throws FileNotFoundException If the file is not found.
     * @throws IOException           If an I/O error occurs.
     */
    DocumentUploadBD uploadFilesRS(String documentalSerial, MultipartFile file, String requestBody)
            throws FileNotFoundException, IOException;
}

