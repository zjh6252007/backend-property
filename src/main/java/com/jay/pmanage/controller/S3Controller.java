package com.jay.pmanage.controller;

import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.service.S3Service;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
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

    @GetMapping("/list")
    public Result<List<String>> getFileList(){
        Map<String,Object> user = ThreadLocalUtil.get();
        String userId = user.get("id").toString();

        List<String> files = s3Service.getFileList(userId);
        if(files != null){
            return Result.success(files);
        }else{
            return Result.error("No Data");
        }
    }

    @GetMapping("/presigned-url/{fileName}")
    public Result<URL> getURL(@PathVariable String fileName){
        Map<String,Object> user = ThreadLocalUtil.get();
        String userId = user.get("id").toString();
        String fullPath = userId + "/" +fileName + ".pdf";
        URL url = s3Service.generateURL(fullPath);
        return Result.success(url);
    }
}
