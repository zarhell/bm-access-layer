package com.biomed.bm_access_layer.documentalmanager.application.usecase;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.in.DocumentUploadBD;
import org.springframework.web.multipart.MultipartFile;


/**
 * The UploadFileService interface defines the contract for uploading files and
 * processing them.
 *
 * Implementing classes must provide the behavior to upload files based on the
 * specified documental serial,
 * the file to be uploaded, and the request body. The uploaded files are
 * processed and a DocumentUploadBD object
 * containing the updated document information is returned. The implementation
 * may handle exceptions such as
 * FileNotFoundException and IOException during the upload process.
 *
 * The UploadFileService interface is sealed, permitting only specific
 * implementing classes.
 */
public interface UploadFileService {
    /**
     * Uploads files and processes them, updating the document information.
     *
     * @param documentalSerial The documental serial.
     * @param file             The file to upload.
     * @param requestBody      The request body.
     * @return The updated DocumentUploadBD.
     * @throws FileNotFoundException If the file is not found.
     * @throws IOException           If an I/O error occurs.
     */

    public DocumentUploadBD uploadFilesRS(String documentalSerial, MultipartFile file, String requestBody)
            throws FileNotFoundException, IOException;
}
