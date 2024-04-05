package com.jay.pmanage.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface S3Service {
    Boolean uploadFile(String userId,MultipartFile file);

    List<String> getFileList(String userId);

    URL generateURL(String filename);
}
