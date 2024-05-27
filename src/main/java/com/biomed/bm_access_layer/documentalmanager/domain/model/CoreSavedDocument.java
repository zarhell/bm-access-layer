package com.biomed.bm_access_layer.documentalmanager.domain.model;

import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumDocumentType;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.DocumentalAreaConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.DocumentalDatesConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.DocumentalUserConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentAreaDto;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.DocumentalUserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "core_saved_document")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NosArgsConstructor
@AllArgsConstructor
public class CoreSavedDocument{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

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
    private String correspondenceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_priority")
    private String documentPriority;

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
    private Integer storageId;
}