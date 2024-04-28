package com.jay.pmanage.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

public interface S3Service {
    Boolean uploadFile(String userId,String propertyAddress,MultipartFile file);

    List<String> getFileList(String userId);

    URL generateURL(String filename);
    void deleteFile(String userId, String fileName);
}
