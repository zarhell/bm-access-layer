package com.biomed.bm_access_layer.documentalmanager.domain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumFinalDisposition;
import com.biomed.bm_access_layer.documentalmanager.domain.common.EnumSupportType;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.OrganitationConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out.SpecificationFeaturesConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.config.YearsRetentionDtoConverter;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.OrganizationDto;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.SpecificationFeaturesDto;
import com.biomed.bm_access_layer.documentalmanager.infrastructure.dto.YearsRetentionDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * The CoreDocumentalRetention class represents a document retention record in the system.
 */
@Data
@Entity
@Table(name="core_documental_retention")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CoreDocumentalRetention {

    @Id
    @GeneratedValue
    private UUID id;

    @Convert(converter = OrganitationConverter.class)
    @Column(name = "organitation")
    private OrganizationDto organitation;

    @Column(name = "dependency_code")
    private String dependencyCode;

    @Convert(converter = SpecificationFeaturesConverter.class)
    @Column(name = "specification_features")
    private SpecificationFeaturesDto specificationFeatures;

    @Convert(converter = YearsRetentionDtoConverter.class)
    @Column(name = "years_retention")
    private YearsRetentionDto yearsRetention;

    @Column(name = "support_type")
    @Enumerated(EnumType.STRING)
    private EnumSupportType supportType;

    @Column(name = "final_disposition")
    @Enumerated(EnumType.STRING)
    private EnumFinalDisposition finalDisposition;

    @Column(name = "procedure")
    private String procedure;


}
