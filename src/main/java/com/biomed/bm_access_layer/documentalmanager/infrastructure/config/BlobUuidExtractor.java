package com.biomed.bm_access_layer.documentalmanager.infrastructure.config;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.util.UUID;

public class BlobUuidExtractor {
    private final Storage storage;

    public BlobUuidExtractor() {
        // Inicializa el cliente de Google Cloud Storage
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    public UUID extractUuidFromBlob(String bucketName, String blobName) {
        // Obtén el Blob de Google Cloud Storage
        BlobId blobId = BlobId.of(bucketName, blobName);
        Blob blob = storage.get(blobId);

        if (blob != null) {
            // Supón que el nombre del archivo en el Blob contiene un UUID
            String blobFileName = blob.getName();
            try {
                // Extrae el UUID del nombre del archivo
                return UUID.fromString(blobFileName);
            } catch (IllegalArgumentException e) {
                // Maneja el caso donde el nombre del archivo no es un UUID válido
                System.err.println("El nombre del archivo no contiene un UUID válido: " + e.getMessage());
            }
        } else {
            System.err.println("No se encontró el Blob con el nombre especificado.");
        }

        return null;
    } }