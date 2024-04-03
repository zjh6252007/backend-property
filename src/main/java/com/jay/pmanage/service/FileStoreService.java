package com.jay.pmanage.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStoreService {
    String uploadFile(MultipartFile file);
    byte[] downloadFile(String filename);
    String deleteFile(String filename);
    List<String> listAllFiles();
}
