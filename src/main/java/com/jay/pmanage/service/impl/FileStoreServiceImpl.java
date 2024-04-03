/*package com.jay.pmanage.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.jay.pmanage.service.FileStoreService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InaccessibleObjectException;
import java.util.List;
@Service
public class FileStoreServiceImpl implements FileStoreService {

    @Value("${bucketName}")
    private String bucketName;

    private final AmazonS3 s3Client;
    public FileStoreServiceImpl(AmazonS3 s3){
        this.s3Client = s3;
    }
    @Override
    public String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try{
            File file1 = convertMultiPartToFile(file);
            PutObjectResult putObjectResult = s3Client.putObject(bucketName,originalFilename,file1);
            return putObjectResult.getContentMd5();
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] downloadFile(String filename) {
        return new byte[0];
    }

    @Override
    public String deleteFile(String filename) {
        return null;
    }

    @Override
    public List<String> listAllFiles() {
        return null;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException{
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
*/