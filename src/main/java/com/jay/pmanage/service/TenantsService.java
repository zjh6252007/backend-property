package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Tenants;

public interface TenantsService {
    public Tenants findAllTenants(String userid);
    public void addTenants(Tenants tenants);
}
