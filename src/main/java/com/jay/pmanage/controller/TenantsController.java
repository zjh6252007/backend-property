package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.service.TenantsService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tenant")
@CrossOrigin(origins = "http://localhost:3000")
public class TenantsController {
    private final TenantsService tenantsService;
    public TenantsController(TenantsService tenantsService){
        this.tenantsService = tenantsService;
    }

    @PostMapping("/add")
    public Result<Tenants> add(@RequestBody Tenants tenants)
    {
        tenantsService.addTenants(tenants);
        return Result.success(tenants);
    }

    @GetMapping("/getAll")
    public Result<List<Tenants>> getAll()
    {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        List<Tenants> tenantsList = tenantsService.findAllTenants(userid);
        return Result.success(tenantsList);
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id)
    {
        tenantsService.deleteTenants(id);
        return Result.success();
    }
}
