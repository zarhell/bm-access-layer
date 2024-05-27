package com.biomed.bm_access_layer.documentalmanager.infrastructure.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import org.apache.commons.io.IOUtils;
import lombok.extern.log4j.Log4j2;

@Log4j2
public final class CustomBase64Utils {

    /**
     * Encodes an InputStream to Base64.
     *
     * @param input The InputStream to encode.
     * @return The Base64 encoded string.
     * @throws IOException if an I/O error occurs.
     */
    public static String encodeToBase64(InputStream input) throws IOException {
        log.debug("Utils | encodeToBase64 | Encoding InputStream to Base64");
        byte[] fileOut = IOUtils.toByteArray(input);
        String fileOutB64 = Base64.getEncoder()
                .encodeToString(fileOut);
        return fileOutB64;
    }

    /**
     * Decodes a Base64 string to a byte array.
     *
     * @param input The Base64 string to decode.
     * @return The decoded byte array.
     * @throws IOException if an I/O error occurs or if the input is not a valid
     *                     Base64 string.
     */
    public static byte[] decodeToBase64(String input) throws IOException {
        log.debug("Utils | decodeToBase64 | Decoding Base64 to byte array");
        try {
            return Base64.getDecoder().decode(input);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid Base64", e);
        }
    }

}