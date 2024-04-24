package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.TenantRegistrationDto;
import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.service.TenantsService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tenant")

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

    @PostMapping("/register")
    public Result<Void> register(@RequestParam("invitation_token") String token, @RequestBody TenantRegistrationDto request)
    {
        tenantsService.register(request.getUsername(), request.getPassword(), token);
        return Result.success();
    }
    @GetMapping("/getAll")
    public Result<List<Tenants>> getAll()
    {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        List<Tenants> tenantsList = tenantsService.findAllTenants(userid);
        return Result.success(tenantsList);
    }

    @GetMapping("/{id}")
    public Result<Tenants> getTenantById(@PathVariable Integer id)
    {
        Tenants tenants = tenantsService.getTenantById(id);
        if(tenants != null) {
            return Result.success(tenants);
        }else{
            return Result.error("Can't find tenant");
        }
    }
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Integer id)
    {
        tenantsService.deleteTenants(id);
        return Result.success();
    }

    @PutMapping("/modify/{id}")
    public Result<Tenants> modify(@PathVariable Integer id,@RequestBody Tenants tenants)
    {
        return Result.success(tenantsService.modifyTenants(id,tenants));
    }
}
