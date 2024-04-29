package com.jay.pmanage.controller;

import com.jay.pmanage.mapper.PropertiesMapper;
import com.jay.pmanage.pojo.Contract;
import com.jay.pmanage.pojo.ContractDto;
import com.jay.pmanage.pojo.Properties;
import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.service.ContractService;
import com.jay.pmanage.service.PropertiesService;
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
    private final ContractService contractService;
    private final PropertiesService propertiesService;
    @Autowired
    public S3Controller(S3Service s3Service,ContractService contractService,PropertiesService propertiesService) {
        this.s3Service = s3Service;
        this.contractService = contractService;
        this.propertiesService = propertiesService;
    }

    @PostMapping("/upload")
    public Result<Void> uploadFile(@ModelAttribute ContractDto contractDto) {
        Map<String,Object> user = ThreadLocalUtil.get();
        String userID = user.get("id").toString();
        /*
        Properties property = propertiesService.findPropertyById(contractDto.getPropertyId());
        String propertyAddress = "";
        if(property != null) {
            propertyAddress = "/" + property.getAddress();
        }*/
        if (s3Service.uploadFile(userID,contractDto.getFile())) {
            Contract contract = new Contract();
            contract.setName(contractDto.getName());
            contract.setStartTime(contractDto.getStartTime());
            contract.setEndTime(contractDto.getEndTime());
            contract.setPropertyId(contractDto.getPropertyId());
            contractService.createContract(contract);
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

    @DeleteMapping("/delete/{fileName}")
    public Result<Void> deleteFile(@PathVariable String fileName)
    {
        Map<String,Object> user = ThreadLocalUtil.get();
        String userId = user.get("id").toString();
        fileName = fileName + ".pdf";
        s3Service.deleteFile(userId,fileName);
        return Result.success();
    }
}
