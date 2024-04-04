package com.jay.pmanage.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.jay.pmanage.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(),metadata));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
