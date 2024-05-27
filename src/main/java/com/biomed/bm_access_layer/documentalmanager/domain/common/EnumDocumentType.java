package com.biomed.bm_access_layer.documentalmanager.domain.common;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum EnumDocumentType {
    DOCUMENTACION_RELATIVA_A_LA_HOJA_CLINICO_ESTADISTICA("DocumentaciOn relativa a la hoja clInico-estadIstica", 0),
    AUTORIZACION_DEL_INGRESO("AutorizaciOn del ingreso", 1),
    INFORME_DE_URGENCIA("Informe de urgencia", 2),
    ANAMNESIS_Y_EXPLORACION_FISICA("Anamnesis y exploraciOn fIsica", 3),
    EVOLUCION("EvoluciOn", 4),
    ORDENES_MEDICAS("Ordenes mEdicas", 5),
    INFORME_DE_ANESTESIA("Informe de anestesia", 6),
    HOJA_DE_INTERCONSULTA("Hoja de intercon sulta", 7),
    INFORMES_DE_EXPLORACIONES_COMPLEMENTARIAS("Informes de exploraciones complementarias", 8),
    CONSENTIMIENTO_INFORMADO("Consentimiento informado", 9),
    INFORME_DE_QUIROFANO_O_REGISTRO_DEL_PARTO("Informe de quirOfano o registro del parto", 10),
    INFORME_DE_ANATOMIA_PATOLOGICA("Informe de anatomIa patolOgica", 11),
    EVOLUCION_Y_PLANIFICACION_DE_CUIDADOS_DE_ENFERMERIA("EvoluciOn y planificaciOn de cuidados de enfermerIa", 12),
    APLICACION_TERAPEUTICA_DE_ENFERMERIA("AplicaciOn terapEutica de enfermerIa", 13),
    GRAFICO_DE_CONSTANTES("Gráfico de constantes", 14),
    INFORME_CLINICO_DE_ALTA("Informe clInico de alta", 15);

    private String type;

    @JsonValue
    private Integer id;

    /**
     * Constructs an EnumDocumentType object with the specified type and ID.
     *
     * @param type The document type.
     * @param id   The ID of the document type.
     */
    private EnumDocumentType(String type, Integer id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    /**
     * Retrieves the EnumDocumentType object associated with the specified ID.
     *
     * @param id The ID of the document type.
     * @return The EnumDocumentType object, or null if no matching document type is found.
     */
    public static EnumDocumentType getDocumentTypeById(int id) {
        return Arrays.stream(EnumDocumentType.values())
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
