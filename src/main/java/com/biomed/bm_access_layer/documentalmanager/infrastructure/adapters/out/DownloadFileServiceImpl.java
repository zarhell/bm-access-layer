package com.biomed.bm_access_layer.documentalmanager.infrastructure.adapters.out;


@Service
public class DownloadFileServiceImpl {

    private GcpCredentialsService gcpCredentials;

    private UploadFileRepository repository;

    public DownloadFileServiceImpl(GcpCredentialsService gcpCredentials, UploadFileRepository repository) {
        this.gcpCredentials = gcpCredentials;
        this.repository = repository;
    }


    @Override
    public DocumentFetchDtoRS fetchDocument(Integer storageId, String userLendCode)
            throws FileNotFoundException, IOException {


        Optional<CoreSavedDocument> existingDocument = repository.findByStorageId(storageId);

        if (!existingDocument.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "requested document wont match with documental serial code");
        }

        DocumentFetchDtoRS response = retrieveDocumentFromStorage(existingDocument);


        updateDocument(userLendCode, existingDocument);

        return response;


    }


    private DocumentFetchDtoRS retrieveDocumentFromStorage(Optional<CoreSavedDocument> existingDocument) throws FileNotFoundException, IOException {
        Storage storage =  gcpCredentials.getCredentialsStorage(ConstansCore.BUCKET_NAME);

        BlobId blobRetrieved = BlobId.of(ConstansCore.BUCKET_NAME,  existingDocument.get().getDocumentName());

        Blob blob = storage.get(blobRetrieved);


        InputStream inputStream = Channels.newInputStream(blob.reader());

        String fileOutB64 = Utils.encodeToBase64(inputStream);

        DocumentFetchDtoRS response = new DocumentFetchDtoRS();

        response.setInputStream(fileOutB64);
        response.setDocumentName(blob.getBlobId().getName());

        return response;
    }


    private void updateDocument(String userLendCode, Optional<CoreSavedDocument> existingDocument) {

        // DocumentalLifeCycleDates documentalDates = new DocumentalLifeCycleDates();

        existingDocument.get().getDocumentalUser().setUserLendCode(userLendCode);
        // existingDocument.get().setDocumentalDates(documentalDates);
    }
}
