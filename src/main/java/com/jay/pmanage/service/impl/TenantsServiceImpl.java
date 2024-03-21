package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.TenantsMapper;
import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.service.TenantsService;
import org.springframework.stereotype.Service;

@Service
public class TenantsServiceImpl implements TenantsService {
    private final TenantsMapper tenantsMapper;
    public TenantsServiceImpl(TenantsMapper tenantsMapper)
    {
        this.tenantsMapper = tenantsMapper;
    }
    @Override
    public Tenants findAllTenants(String userid) {
        return null;
    }

    @Override
    public void addTenants(Tenants tenants) {
        tenantsMapper.add(tenants);
    }
}
