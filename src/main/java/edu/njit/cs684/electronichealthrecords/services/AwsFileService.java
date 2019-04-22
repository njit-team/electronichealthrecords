package edu.njit.cs684.electronichealthrecords.services;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import edu.njit.cs684.electronichealthrecords.domain.dbmodel.Patient;
import edu.njit.cs684.electronichealthrecords.domain.restmoel.DocumentRest;
import edu.njit.cs684.electronichealthrecords.exceptions.PatientDocumentNotSentException;
import edu.njit.cs684.electronichealthrecords.exceptions.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class AwsFileService implements FileService {

    public static final String PATIENT_FILE_KEY_NAME = "fileName";

    @Autowired
    private PatientService patientService;
    @Autowired
    private AmazonS3Client amazonS3Client;
    private String bucketName = "qa2019/patient-documents";
    private MongoTemplate mongoTemplate;

    public void savePatientDocument(DocumentRest documentRest) throws IOException {
        //save the photo in aws first
        if (Objects.isNull(documentRest) && Objects.isNull(documentRest.getDocument())) {
            throw new PatientDocumentNotSentException("Patient Document data was not sent.");
        }

        Patient patientById = patientService.findPatientById(documentRest.getPatientId());
        if (Objects.isNull(patientById)) {
            throw new PatientNotFoundException("Patient not found in database.");
        }


        byte[] bytes = documentRest.getDocument().getBytes();
        String key = String.format("%s_%s_%s", UUID.nameUUIDFromBytes(bytes).toString(), documentRest.getPatientId(), documentRest.getDocument().getOriginalFilename());

        Map<String, String> patientMetaData = new HashMap<>();
        patientMetaData.put(PATIENT_FILE_KEY_NAME, key);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(bytes.length);
        objectMetadata.setUserMetadata(patientMetaData);

        PutObjectRequest request = new PutObjectRequest(bucketName,
                key, new ByteArrayInputStream(bytes), objectMetadata);
        PutObjectResult putObjectResult = amazonS3Client.putObject(request);
        if (Objects.nonNull(putObjectResult) && Objects.nonNull(putObjectResult.getETag())) {
            //save the key with other details in mongodb
            patientById.getRecordFiles().add(key);
        }

        patientService.getPatientRepository().save(patientById);
        return;
    }

    public byte[] retrievePatientDocument(String documentName) throws IOException {
        if (Objects.isNull(documentName)) {
            throw new RuntimeException(String.format("Please send Patient Document Name."));
        }
        GetObjectRequest patientDocumentRequest = new GetObjectRequest(bucketName, documentName);
        S3Object patientDocument = amazonS3Client.getObject(patientDocumentRequest);
        S3ObjectInputStream objectContent = patientDocument.getObjectContent();
        byte[] patientDocumentBytes = objectContent.readAllBytes();
        return patientDocumentBytes;
    }

    public void deletePatientDocument(String documentName, String patientId) throws IOException {
        if (Objects.isNull(documentName) || Objects.isNull(patientId)) {
            throw new RuntimeException(String.format("Please send Patient Document Name and Patient Id."));
        }
        Patient patientById = patientService.findPatientById(patientId);
        if (Objects.isNull(patientById)) {
            throw new PatientNotFoundException("Patient not found in database.");
        }
        if (patientById.getRecordFiles().contains(documentName)) {
            DeleteObjectRequest deleteRequest = new DeleteObjectRequest(bucketName, documentName);
            amazonS3Client.deleteObject(deleteRequest);
        }else{
            throw new RuntimeException(String.format("documentName: %s does not exist for patient: %s", documentName, patientId));
        }
        return;
    }
}
