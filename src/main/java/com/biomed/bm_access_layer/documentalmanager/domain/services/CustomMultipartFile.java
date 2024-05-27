package com.biomed.bm_access_layer.documentalmanager.domain.services;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Custom implementation of the Spring Framework's MultipartFile interface.
 * This class represents a multipart file and provides additional
 * functionalities
 * for working with file content and streams.
 */
@Log4j2
@Getter
public class CustomMultipartFile implements MultipartFile {

    private final byte[] fileContent;

    private String fileName;

    private File file;

    private FileOutputStream fileOutputStream;

    private final FileOutputStreamFactory fileOutputStreamFactory;

    public CustomMultipartFile(byte[] fileData, String name, FileOutputStreamFactory fileOutputStreamFactory) {
        this.fileContent = fileData;
        this.fileName = name;
        this.fileOutputStreamFactory = fileOutputStreamFactory;
    }

    /**
     * Transfers the file content to the specified destination file.
     *
     * @param dest The destination file to which the file content should be
     *             transferred.
     * @throws IOException           If an I/O error occurs during the transfer.
     * @throws IllegalStateException If the custom multipart file is in an illegal
     *                               state for transfer.
     */
    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        log.debug("CustomMultipartFile | transferTo | Transferring file content to destination file");
        try {
            fileOutputStream = fileOutputStreamFactory.createFileOutputStream(dest);
            fileOutputStream.write(fileContent);
        } catch (IOException e) {
            log.error("Failed to create FileOutputStream for destination file: {}", dest.getAbsolutePath());
            throw e;
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    /**
     * Clears the output streams associated with the custom multipart file.
     * This method flushes and closes the file output stream, and deletes the
     * temporary file.
     *
     * @throws IOException If an I/O error occurs while clearing the output streams.
     */
    public void clearOutStreams() throws IOException {
        log.debug("CustomMultipartFile | clearOutStreams | Clearing output streams");
        if (fileOutputStream != null) {
            fileOutputStream.flush();
            fileOutputStream.close();
            fileOutputStream = null;
        }

    }

    /**
     * Retrieves the contents of the file as a byte array.
     *
     * @return The file content as a byte array.
     * @throws IOException If an I/O error occurs while retrieving the file content.
     */
    @Override
    public byte[] getBytes() throws IOException {
        log.debug("CustomMultipartFile | getBytes | Retrieving file content as byte array");
        return fileContent;
    }

    /**
     * Retrieves an input stream for reading the file content.
     *
     * @return An input stream for reading the file content.
     * @throws IOException If an I/O error occurs while retrieving the input stream.
     */
    @Override
    public InputStream getInputStream() throws IOException {
        log.debug("CustomMultipartFile | getInputStream | Retrieving input stream for reading the file content");
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return null;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
