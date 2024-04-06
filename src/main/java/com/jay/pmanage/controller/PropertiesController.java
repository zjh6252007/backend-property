package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Properties;
import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.service.PropertiesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
    private final PropertiesService propertiesService;
    PropertiesController(PropertiesService propertiesService)
    {
        this.propertiesService = propertiesService;
    }

    @PostMapping("/add")
    public Result<Void> addProperties(@RequestBody Properties properties)
    {
        propertiesService.addProperties(properties);
        return Result.success();
    }

}
