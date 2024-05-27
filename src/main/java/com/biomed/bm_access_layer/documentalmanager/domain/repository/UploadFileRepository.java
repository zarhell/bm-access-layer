package com.biomed.bm_access_layer.documentalmanager.domain.repository;


import  com.biomed.bm_access_layer.documentalmanager.domain.model.CoreSavedDocument;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * The UploadFileRepository interface provides CRUD operations for managing
 * CoreSavedDocument entities in the database.
 * It extends the JpaRepository interface, which provides generic CRUD
 * operations.
 */
public interface UploadFileRepository extends JpaRepository<CoreSavedDocument, UUID> {

    /**
     * Retrieves an optional CoreSavedDocument entity based on the documental
     * serial.
     *
     * @param serial The documental serial.
     * @return An optional CoreSavedDocument entity.
     */
    @Query(value = "SELECT c FROM CoreSavedDocument c WHERE c.documentalSerial = ?1")
    Optional<CoreSavedDocument> findByDocumentalSerial(String serial);

    /**
     * Retrieves an optional CoreSavedDocument entity based on the storage ID.
     *
     * @param storageId The storage ID.
     * @return An optional CoreSavedDocument entity.
     */
    @Query(value = "SELECT c FROM CoreSavedDocument c WHERE c.storageId = ?1")
    Optional<CoreSavedDocument> findByStorageId(UUID storageId);

}