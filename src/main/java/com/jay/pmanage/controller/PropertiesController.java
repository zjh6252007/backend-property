package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Properties;
import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.service.PropertiesService;
import com.jay.pmanage.service.UserService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
    private final PropertiesService propertiesService;
    private final UserService userService;
    PropertiesController(PropertiesService propertiesService, UserService userService)
    {
        this.propertiesService = propertiesService;
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public Result<List<Properties>> getAllProperties(){
        Map<String,Object> propertiesMap = ThreadLocalUtil.get();
        int userid = (Integer)propertiesMap.get("id");
        List<Properties> propertiesList = propertiesService.getAll(userid);
        return Result.success(propertiesList);
    }

    @GetMapping("/getMyHome")
    public Result<Properties> getMyHome(){
        return Result.success(propertiesService.findTenantProperty());
    }

    @GetMapping("/{id}")
    public Result<Properties> getPropertyInfo(@PathVariable Integer id){
        Properties property = propertiesService.findPropertyById(id);
        Map<String,Object> propertiesMap = ThreadLocalUtil.get();
        int ownerId = (Integer)propertiesMap.get("id");
        if(property != null && ownerId == property.getOwnerId()){
        return Result.success(property);
        }else {
            return Result.error("cant find property");
        }
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
        Properties properties1 = propertiesService.findPropertyById(id);
        return Result.success(properties1);
    }

    @GetMapping("/getMyProperty")
    public Result<Properties> getMyProperty(){
        Integer propertyId = userService.getPropertyIdByTenantId();
        Properties properties = propertiesService.findPropertyById(propertyId);
        return Result.success(properties);
    }
}
