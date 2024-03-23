package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Tenants;

import java.util.List;

public interface TenantsService {
    public List<Tenants> findAllTenants(Integer userid);
    public void addTenants(Tenants tenants);
    public void deleteTenants(Integer tenantsId);
}
