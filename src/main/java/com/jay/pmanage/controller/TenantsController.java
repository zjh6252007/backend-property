package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.service.TenantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant")
public class TenantsController {
    private final TenantsService tenantsService;
    public TenantsController(TenantsService tenantsService){
        this.tenantsService = tenantsService;
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody Tenants tenants)
    {
        tenantsService.addTenants(tenants);
        return Result.success();
    }
}
