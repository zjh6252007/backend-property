package com.jay.pmanage.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.*;

public class AwsCloudUtil {
    private AWSCredentials awsCredentials(String accessKey,String secretKey){
        return new BasicAWSCredentials(
                accessKey,
                secretKey
        );
    }

    private AmazonS3 aswS3ClientBuilder(String accessKey,String secretKey){
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials(accessKey,secretKey)))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public void uploadFile(String filename, byte[] fileBytes, String accessKey, String secretKey, String bucket){
        AmazonS3 s3client = aswS3ClientBuilder(accessKey,secretKey);
        File file = new File(filename);
        try(OutputStream os = new FileOutputStream(file)){
            os.write(fileBytes);
        } catch (IOException e){
            e.printStackTrace();
        }
        s3client.putObject(bucket,filename,file);
    }

    public S3ObjectInputStream downloadFileFromS3(String filename, String accessKey, String secretKey, String bucket){
        AmazonS3 s3client = aswS3ClientBuilder(accessKey,secretKey);
        S3Object s3Object = s3client.getObject(bucket,filename);
        return s3Object.getObjectContent();
    }
}
