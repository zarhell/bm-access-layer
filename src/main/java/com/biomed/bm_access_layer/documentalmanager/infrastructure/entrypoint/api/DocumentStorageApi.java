package com.biomed.bm_access_layer.documentalmanager.infrastructure.entrypoint.api;

import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.in.DocumentUploadBD;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public interface DocumentStorageApi {

    @PostMapping(value = "{id}")
    ResponseEntity<DocumentUploadBD> documentUpload(
            @PathVariable("id") String documentalSerial,
            @RequestParam MultipartFile file,
            @RequestParam("request_body") String requestBody
    ) throws FileNotFoundException, IOException;

    @GetMapping
    ResponseEntity<byte[]> documentDownload(
            @RequestParam("storage_id") UUID storageId,
            @RequestParam("user_lend_code") String userLendCode
    ) throws FileNotFoundException, IOException;
}