package com.jay.pmanage.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.jay.pmanage.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class S3ServiceImpl implements S3Service {
    private final AmazonS3 s3Client;
    private final String bucketName = "pdf-bucket-zjh";
    @Autowired
    public S3ServiceImpl(AmazonS3 s3Client){
        this.s3Client = s3Client;
    }

    @Override
    public Boolean uploadFile(String userId,MultipartFile file) {
        String fileName = userId + "/" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        metadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(),metadata));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getFileList(String userId) {
        List<String> fileNames = new ArrayList<>();
        String prefix = userId + "/";

        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix(prefix);
        ListObjectsV2Result result;

        do{
            result = s3Client.listObjectsV2(req);
            for(S3ObjectSummary objectSummary : result.getObjectSummaries()){
                String key = objectSummary.getKey();
                String name = key.substring(key.indexOf('/') + 1);
                String nameWithoutExtension = name.contains(".") ?name.substring(0, name.lastIndexOf('.')):name;
                fileNames.add(nameWithoutExtension);
            }
            req.setContinuationToken(result.getContinuationToken());
        }while(result.isTruncated());
        return fileNames;
    }

    @Override
    public URL generateURL(String filename) {
        Date expireDate = new Date();
        long expTimeMillis = expireDate.getTime();
        expTimeMillis += 1000 * 30 * 60;
        expireDate.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,filename)
                .withMethod(HttpMethod.GET)
                .withExpiration(expireDate);

        return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    }

    @Override
    public void deleteFile(String userId, String fileName) {
       try{
           String fileKey = userId + "/" + fileName;
           DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName,fileKey);
           s3Client.deleteObject(deleteObjectRequest);
       }catch (AmazonServiceException e){
           e.printStackTrace();
       }
    }
}
