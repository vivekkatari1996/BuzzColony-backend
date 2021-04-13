package com.idea.buzzcolony.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.fasterxml.uuid.Generators;
import com.idea.buzzcolony.dto.vimeo.FileDto;
import com.idea.buzzcolony.service.S3Service;
import com.idea.buzzcolony.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.UUID;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Service
public class S3ServiceImpl implements S3Service {

    private static final Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);

    @Value("${app.aws_s3_bucketName}")
    private String bucketName;

    @Override
    public FileDto getPreSignedUrlForUpload(FileDto fileDto) throws Exception {
        if (fileDto.getUuid().isEmpty()) {
            fileDto.setUuid(UUID.fromString(Generators.timeBasedGenerator().generate().toString()).timestamp() + "");
        }
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();
        // Set the pre-signed URL to expire after 15 minutes.
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 15;
        expiration.setTime(expTimeMillis);

        // Generate the pre-signed URL.
        logger.info("Generating pre-signed URL for Upload");
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileDto.getUuid())
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration);
        generatePresignedUrlRequest.addRequestParameter(Headers.S3_USER_METADATA_PREFIX + "name", fileDto.getName());
        generatePresignedUrlRequest.addRequestParameter(Headers.S3_USER_METADATA_PREFIX + "uuid", fileDto.getUuid());
        generatePresignedUrlRequest.addRequestParameter(Headers.S3_USER_METADATA_PREFIX + "referencetype", fileDto.getType());
        generatePresignedUrlRequest.addRequestParameter(Headers.S3_USER_METADATA_PREFIX + "referenceid", fileDto.getRefId() + "");

        generatePresignedUrlRequest.setContentType(fileDto.getType());

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        fileDto.setDocumentUrl(url.toString());

        return fileDto;
    }

    @Override
    public String getPreSignedUrlForDownload(String uuid) {
        String result = "";
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_1).build();
            // Set the presigned URL to expire after 15 minutes.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * 15;
            expiration.setTime(expTimeMillis);

            // Generate the presigned URL.
            System.out.println("Generating pre-signed URL.");
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest("buzz-colony-profile-pic", uuid)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
            result = url.toString();
        } catch (AmazonServiceException e) {
            logger.error("Error! ", e);
        } catch (SdkClientException e) {
            logger.error("Error! ", e);
        }
        return result;
    }

}
