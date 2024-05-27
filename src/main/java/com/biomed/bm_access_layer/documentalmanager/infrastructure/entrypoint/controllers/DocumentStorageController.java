package com.biomed.bm_access_layer.documentalmanager.infrastructure.entrypoint.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import com.biomed.bm_access_layer.documentalmanager.application.usecase.DownloadFileService;
import com.biomed.bm_access_layer.documentalmanager.application.usecase.UploadFileService;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.in.DocumentUploadBD;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.CustomBase64Utils;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentFetchDtoRS;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.entrypoint.api.DocumentStorageApi;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/document")
public class DocumentStorageController implements DocumentStorageApi {

    private static final String CACHE_CONTROL_SETTINGS = "must-revalidate, post-check=0, pre-check=0";
    private final UploadFileService uploadFileService;
    private final DownloadFileService downloadFileService;

    public DocumentStorageController(final UploadFileService uploadFileService, final DownloadFileService downloadFileService) {
        this.uploadFileService = uploadFileService;
        this.downloadFileService = downloadFileService;
    }

    @Override
    public ResponseEntity<DocumentUploadBD> documentUpload(
            @PathVariable("id") final String documentalSerial,
            @RequestParam final MultipartFile file,
            @RequestParam("request_body") final String requestBody
    ) throws FileNotFoundException, IOException {
        log.info("DocumentStorageController | documentUpload | Begin upload file.");
        return new ResponseEntity<>(uploadFileService.uploadFilesRS(documentalSerial, file, requestBody), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> documentDownload(
            @RequestParam("storage_id") final UUID storageId,
            @RequestParam("user_lend_code") final String userLendCode
    ) throws FileNotFoundException, IOException {
        log.info("DocumentStorageController | documentDownload | Begin download file.");
        final DocumentFetchDtoRS documentFetch = downloadFileService.fetchDocument(storageId, userLendCode);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename(documentFetch.getDocumentName()).build());
        headers.setCacheControl(CACHE_CONTROL_SETTINGS);

        return new ResponseEntity<>(CustomBase64Utils.decodeToBase64(documentFetch.getInputStream()), headers, HttpStatus.OK);
    }
}