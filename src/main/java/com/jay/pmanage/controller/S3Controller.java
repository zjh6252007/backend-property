package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.service.S3Service;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/file")
public class S3Controller {

    private final S3Service s3Service;

    @Autowired
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public Result<Void> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String,Object> user = ThreadLocalUtil.get();
        String userID = user.get("id").toString();

        if (s3Service.uploadFile(userID,file)) {
            return Result.success();
        } else {
            return Result.error("Uploaded Failed");
        }
    }
}
