package com.biomed.bm_access_layer.documentalmanager.domain.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileOutputStreamFactory {
    public FileOutputStream createFileOutputStream(File dest) throws FileNotFoundException {
        return new FileOutputStream(dest);
    }
}
