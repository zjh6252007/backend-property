package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Properties;
import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.service.PropertiesService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
    private final PropertiesService propertiesService;
    PropertiesController(PropertiesService propertiesService)
    {
        this.propertiesService = propertiesService;
    }

    @GetMapping("/getAll")
    public Result<List<Properties>> getAllProperties(){
        Map<String,Object> propertiesMap = ThreadLocalUtil.get();
        int userid = (Integer)propertiesMap.get("id");
        List<Properties> propertiesList = propertiesService.getAll(userid);
        return Result.success(propertiesList);
    }

    @GetMapping("/{id}")
    public Result<Properties> getPropertyInfo(@PathVariable Integer id){
        Properties property = propertiesService.findPropertyById(id);
        return Result.success(property);
    }

    @PostMapping("/add")
    public Result<Properties> addProperties(@RequestBody Properties properties)
    {
        propertiesService.addProperties(properties);
        return Result.success(properties);
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteProperties(@PathVariable Integer id)
    {
        propertiesService.deleteProperties(id);
        return Result.success();
    }

    @PutMapping("/modify/{id}")
    public Result<Properties> modifyProperties(@PathVariable Integer id,@RequestBody Properties properties)
    {
        propertiesService.modifyProperties(id,properties);
        return Result.success(properties);
    }
}
