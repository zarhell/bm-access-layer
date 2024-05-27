package com.biomed.bm_access_layer.documentalmanager.domain.model;

import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumCorrespondenceType;
import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumDocumentPriority;
import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumDocumentType;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.DocumentalAreaConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.DocumentalDatesConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.DocumentalUserConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentAreaDto;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentalUserDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Represents a saved document in the core system.
 * It contains various properties related to the document, such as its identification,
 * type, associated area, user, lifecycle dates, and storage details.
 */
@Entity
@Data
@Table(name="core_saved_document")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
public class CoreSavedDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "documental_serial")
    private String documentalSerial;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private EnumDocumentType documentType;

    @Convert(converter = DocumentalAreaConverter.class)
    @Column(name = "document_area")
    private DocumentAreaDto documentArea;

    @Convert(converter = DocumentalUserConverter.class)
    @Column(name = "documental_user")
    private DocumentalUserDto documentalUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "correspondence_type")
    private EnumCorrespondenceType correspondenceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_priority")
    private EnumDocumentPriority documentPriority;

    @Column(name = "printed_tag")
    private String printedTag;

    @Convert(converter = DocumentalDatesConverter.class)
    @Column(name = "documental_dates")
    private DocumentalLifeCycleDates documentalDates;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "observations")
    private String observations;

    @Column(name = "storage_id")
    private UUID storageId;

    @Column(name = "document_path")
    private String documentPath;
}