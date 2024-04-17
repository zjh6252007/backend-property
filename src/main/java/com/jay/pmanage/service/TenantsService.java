package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Tenants;

import java.util.List;

public interface TenantsService {
    List<Tenants> findAllTenants(Integer userid);
    void addTenants(Tenants tenants);
    void deleteTenants(Integer tenantsId);

    Tenants getTenantById(Integer tenantId);
    void modifyTenants(Integer tenantsId,Tenants tenants);
}
