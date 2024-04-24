package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Tenants;

import java.util.List;

public interface TenantsService {
    List<Tenants> findAllTenants(Integer userid);
    void addTenants(Tenants tenants);
    void deleteTenants(Integer tenantsId);
    Tenants getTenantById(Integer tenantId);
    Tenants modifyTenants(Integer tenantsId,Tenants tenants);
    void register(String username,String password,String token);
}
