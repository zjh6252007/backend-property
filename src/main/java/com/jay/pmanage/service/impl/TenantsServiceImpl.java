package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.TenantsMapper;
import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.service.TenantsService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TenantsServiceImpl implements TenantsService {
    private final TenantsMapper tenantsMapper;
    public TenantsServiceImpl(TenantsMapper tenantsMapper)
    {
        this.tenantsMapper = tenantsMapper;
    }
    @Override
    public List<Tenants> findAllTenants(Integer userid) {
        return tenantsMapper.findAll(userid);
    }

    @Override
    public void addTenants(Tenants tenants) {
        Map<String,Object> tenantMap = ThreadLocalUtil.get();
        Integer userid = (Integer) tenantMap.get("id");
        tenants.setCreateUser(userid);
        tenantsMapper.add(tenants);
    }

    public void deleteTenants(Integer tenantsId)
    {
        tenantsMapper.delete(tenantsId);
    }
}
