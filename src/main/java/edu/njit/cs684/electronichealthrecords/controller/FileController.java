package edu.njit.cs684.electronichealthrecords.controller;

import edu.njit.cs684.electronichealthrecords.domain.restmoel.DocumentRest;
import edu.njit.cs684.electronichealthrecords.services.AwsFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/files")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private AwsFileService awsFileService;

    @PostMapping(value = "/upload-document", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadPatientDocument(DocumentRest locationPhotoRest) throws Exception {
        logger.info(locationPhotoRest.toString());
        this.awsFileService.savePatientDocument(locationPhotoRest);
    }

    @GetMapping(value = "/retrieve-document/{documentName}", consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.ALL_VALUE, MediaType.ALL_VALUE, MediaType.ALL_VALUE})
    public ResponseEntity<byte[]> retrieveLocationPhotoFromS3(@PathVariable String documentName) throws IOException {
        logger.info("Request received for Patient Document having Name : " + documentName);
        byte[] locationPhotoBytesByEmail = this.awsFileService.retrievePatientDocument(documentName);

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(locationPhotoBytesByEmail, headers, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(value = "/delete-document/{patientId}/{patientDocumentName}")
    public void deletePatientDocument(@PathVariable String patientDocumentName, @PathVariable String patientId) throws IOException {
        awsFileService.deletePatientDocument(patientDocumentName, patientId);
    }
}