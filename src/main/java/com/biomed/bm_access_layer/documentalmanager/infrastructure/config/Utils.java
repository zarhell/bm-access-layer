package com.biomed.bm_access_layer.documentalmanager.infrastructure.config;


import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;
import java.util.UUID;

import org.hashids.Hashids;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.extern.log4j.Log4j2;

@Log4j2
public final class Utils {

    public static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Serializes an object to its JSON representation.
     *
     * @param value The object to serialize.
     * @return The JSON string representation of the object.
     */
    public static String serializeObject(final Object value) {
        try {
            log.info("Utils | serializeObject | Serializing object");
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("Utils | serializeObject | Serialization Failed", e);
            return "";
        }
    }

    /**
     * Deserializes a JSON string to an object of the specified type.
     *
     * @param src        The JSON string to deserialize.
     * @param valueType  The class representing the type of the object to
     *                   deserialize to.
     * @param paramClass Optional parameter classes used for constructing parametric
     *                   types.
     * @param <T>        The type of the object to deserialize to.
     * @return The deserialized object.
     */
    public static <T> T deserializeObject(final String src, final Class<T> valueType, final Class<?>... paramClass) {
        log.info("Utils | deserializeObject | Deserializing object");
        try {
            return MAPPER.readValue(src, MAPPER.getTypeFactory().constructParametricType(valueType, paramClass));
        } catch (JsonMappingException e) {
            log.warn("Utils | deserializeObject | Failed deserialize. source:|{}|, message error:{}", src, e.getMessage());
            return null;
        } catch (JsonProcessingException e) {
            log.warn("Utils | deserializeObject | Failed deserialize. source:|{}|, message error:{}", src, e.getMessage());
            return null;
        }

    }

    /**
     * Rounds a double value to the specified number of decimal places.
     *
     * @param value  The value to round.
     * @param places The number of decimal places to round to.
     * @return The rounded value.
     * @throws IllegalArgumentException if the value or places is null, or places is
     *                                  negative.
     */
    public static Double roundDouble(final Double value, final Integer places) {
        log.debug("Utils | roundDouble | Rounding double value");
        if (value == null || places == null || places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Generates a unique identifier by combining a random UUID with the current
     * epoch second.
     *
     * @return The generated unique identifier.
     */
    public static String generateIdentifier() {
        log.debug("Utils | generateIdentifier | Generating identifier");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        return UUID.randomUUID().toString() + "_" + zdt.toEpochSecond();
    }

    /**
     * Generates a short identifier using the Hashids library, combining the
     * generated identifier with the current epoch second.
     *
     * @return The generated short identifier.
     */
    public static String generateShortIdentifier() {
        log.debug("Utils | generateShortIdentifier | Generating short identifier");
        Hashids hashids = new Hashids(Utils.generateIdentifier());
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        String hash = hashids.encode(1l, 2l, 3l, zdt.toEpochSecond());
        return hash + "_" + zdt.toEpochSecond();
    }

    /**
     * Converts an object to its JSON string representation using the default
     * ObjectMapper.
     *
     * @param object The object to convert.
     * @return The JSON string representation of the object.
     */
    public static String objectToJsonString(final Object object) {
        log.debug("Utils | objectToJsonString | Converting object to JSON string");
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Formats a LocalDateTime value as a string with the pattern
     * "dd/MM/yyyy-HH:mm".
     *
     * @param value The LocalDateTime value to format.
     * @return The formatted string representation of the LocalDateTime value.
     */
    public static String formatDatetime(final LocalDateTime value) {
        log.debug("Utils | formatDatetime | Formatting LocalDateTime");
        return DateTimeFormatter.ofPattern("dd/MM/yyyy'-'HH:mm")
                .format(value);
    }

    /**
     * Formats a LocalDateTime value as a string with the pattern "dd MMMM YYYY".
     *
     * @param value The LocalDateTime value to format.
     * @return The formatted string representation of the LocalDateTime value.
     */
    public static String formatFullDate(final LocalDateTime value) {
        log.debug("Utils | formatFullDate | Formatting LocalDateTime as full date");
        return DateTimeFormatter.ofPattern("dd MMMM YYYY")
                .toFormat()
                .format(value);
    }

    /**
     * Formats a LocalDate value as a string with the pattern "dd/MM/yyyy".
     *
     * @param value The LocalDate value to format.
     * @return The formatted string representation of the LocalDate value.
     */
    public static String formatDate(final LocalDate value) {
        log.debug("Utils | formatDate | Formatting LocalDate");
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").toFormat().format(value);
    }

    /**
     * Formats a LocalDateTime value as a string with the pattern "HH:mm".
     *
     * @param value The LocalDateTime value to format.
     * @return The formatted string representation of the LocalDateTime value.
     */
    public static String formatTime(final LocalDateTime value) {
        log.debug("Utils | formatTime | Formatting LocalDateTime as time");
        return DateTimeFormatter.ofPattern("HH:mm").toFormat().format(value);
    }

    /**
     * Formats an integer duration value as a string with the pattern "HH:mm".
     *
     * @param value The integer duration value to format.
     * @return The formatted string representation of the duration value.
     */
    public static String formatDuration(final Integer value) {
        log.debug("Utils | formatDuration | Formatting duration");
        return String.format("%02d:%02d", (value / 60), (value % 60));
    }

    /**
     * Converts an object from one type to another using serialization and
     * deserialization.
     *
     * @param fromValue  The object to convert.
     * @param valueType  The class representing the type of the object to convert
     *                   to.
     * @param paramClass Optional parameter classes used for constructing parametric
     *                   types.
     * @param <T>        The type of the object to convert to.
     * @return The converted object.
     */
    public static <T> T convertValue(Object fromValue, final Class<T> valueType, final Class<?>... paramClass) {
        log.debug("Utils | convertValue | Converting value");
        return deserializeObject(serializeObject(fromValue), valueType, paramClass);
    }

    /**
     * Constructs the file path for a resource located in the "resources" directory
     * of the project.
     *
     * @param filename The name of the resource file.
     * @return The file path to the resource file.
     */
    public static String filePathFromResources(String filename) {

        StringJoiner filePath = new StringJoiner(File.separator);
        filePath.add("src").add("main").add("resources").add("files").add(filename);
        //C:\Users\johan.zubietam\Documents\training\cgpCore\src\main\resources\files\request_body.json
        return filePath.toString();
    }

}
