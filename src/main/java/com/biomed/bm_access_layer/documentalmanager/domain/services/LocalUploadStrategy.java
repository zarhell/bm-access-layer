package com.biomed.bm_access_layer.documentalmanager.domain.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

import com.biomed.bm_access_layer.documentalmanager.application.usecase.UploadStrategy;
import com.biomed.bm_access_layer.documentalmanager.domain.model.CoreSavedDocument;
import com.biomed.bm_access_layer.documentalmanager.domain.repository.UploadFileRepository;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.in.DocumentUploadBD;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The LocalUploadStrategy class implements the UploadStrategy interface and
 * provides the behavior for uploading files
 * to the local file system and updating the corresponding document information.
 *
 * This strategy saves the uploaded file to a local directory specified by the
 * RESOURCES_FILE constant, enriches the
 * document parameters, and saves the document to the database through the
 * UploadFileRepository.
 */
public class LocalUploadStrategy implements UploadStrategy {

    private static final String RESOURCES_FILE = new StringJoiner(File.separator)
            .add("src")
            .add("main")
            .add("resources")
            .toString();
    private static final Logger logger = LoggerFactory.getLogger(LocalUploadStrategy.class);
    private UploadFileRepository repository;

    public LocalUploadStrategy(UploadFileRepository repository) {
        this.repository = repository;
    }

    /**
     * Uploads files to the local file system, enriches the document parameters, and
     * saves the document information.
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
        logger.info("LocalUploadStrategy | uploadFilesRS | Start upload process");

        String filename = file.getOriginalFilename();
        File filePath = createFilePath(documentalSerial, filename);
        CoreSavedDocument documentToProcess = repository.findByDocumentalSerial(documentalSerial)
                .orElse(new CoreSavedDocument());
        enrichDocumentParams(documentalSerial, filePath, documentToProcess);
        DocumentUploadBD document = processDocument(documentalSerial, requestBody, filePath, documentToProcess);

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(file.getBytes());
            logger.info("LocalUploadStrategy | uploadFilesRS | File successfully written to disk");
        } catch (IOException e) {
            logger.error("LocalUploadStrategy | uploadFilesRS | Failed to write file to disk", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed upload attempt", e);
        }

        logger.debug("LocalUploadStrategy | uploadFilesRS | creating and uploading document completed");

        repository.save(documentToProcess);
        logger.info("LocalUploadStrategy | uploadFilesRS | Upload process completed");
        return document;
    }

    private void enrichDocumentParams(String documentalSerial, File filePath, CoreSavedDocument documentToProcess) {
        logger.debug("LocalUploadStrategy | enrichDocumentParams | enrtich fields of the document");
        LocalDateTime documentCreationDetails = LocalDateTime.now();

        documentToProcess.setDocumentPath(filePath.getAbsolutePath());
        documentToProcess.getDocumentalDates().setDocumentCreationDetails(documentCreationDetails);
        documentToProcess.setDocumentalSerial(documentalSerial);
        documentToProcess.setStorageId(generateUniqueStorageId());
    }

    private DocumentUploadBD processDocument(String documentalSerial, String requestBody, File filePath,
                                             CoreSavedDocument documentToProcess) {
        logger.debug("LocalUploadStrategy | processDocument | creating and uploading document");
        DocumentUploadBD document = Utils.deserializeObject(requestBody, DocumentUploadBD.class);
        BeanUtils.copyProperties(document.getDocumentProperties(), documentToProcess);
        return document;
    }

    private File createFilePath(String documentalSerial, String filename) {
        logger.debug("LocalUploadStrategy | createFilePath | start creating path name for the document");
        String extension = getExtension(filename);
        String baseFilename = getBaseFilename(filename);
        String filePath = String.format("%s_%s_%s_%s.%s", RESOURCES_FILE, baseFilename, documentalSerial,
                LocalDate.now().toString(), extension);
        logger.debug("LocalUploadStrategy | createFilePath | creating path name for the document completed" + filePath);
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            logger.info("LocalUploadStrategy | createFilePath | creating directory to store the document");
            boolean created = file.getParentFile().mkdirs();
            if (!created) {
                throw new IllegalStateException(
                        "Failed to create directory: " + file.getParentFile().getAbsolutePath());
            }
        }
        return file;
    }

    private String getExtension(String filename) {
        logger.debug("LocalUploadStrategy | getExtension | getting file name extention to the document");
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    private String getBaseFilename(String filename) {
        logger.debug("LocalUploadStrategy | getBaseFilename | getting file main name to the document");
        return filename.substring(0, filename.lastIndexOf("."));
    }

    private UUID generateUniqueStorageId() {
        logger.debug("LocalUploadStrategy | generateUniqueStorageId | start creating a new unique storage ID");
        Set<UUID> existingIds = repository.findAll()
                .stream()
                .map(CoreSavedDocument::getStorageId)
                .collect(Collectors.toSet());

        UUID storageId;

        do {
            storageId = UUID.randomUUID();
        } while (existingIds.contains(storageId));

        logger.debug("LocalUploadStrategy | generateUniqueStorageId | new unique storage ID created: " + storageId);
        return storageId;
    }
}
