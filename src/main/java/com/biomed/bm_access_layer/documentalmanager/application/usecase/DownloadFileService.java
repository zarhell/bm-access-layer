package com.biomed.bm_access_layer.documentalmanager.application.usecase;

import java.io.FileNotFoundException;
import java.io.IOException;
public interface DownloadFileService {
    public DocumentFetchDtoRS fetchDocument(Integer storageId, String userLendCode)
            throws FileNotFoundException, IOException;

}
