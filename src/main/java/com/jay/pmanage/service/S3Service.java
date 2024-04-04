package com.jay.pmanage.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {
    Boolean uploadFile(String userId,MultipartFile file);
}
