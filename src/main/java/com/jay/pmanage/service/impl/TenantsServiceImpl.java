package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.PropertiesMapper;
import com.jay.pmanage.mapper.TenantsMapper;
import com.jay.pmanage.pojo.Properties;
import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.service.EmailService;
import com.jay.pmanage.service.TenantsService;
import com.jay.pmanage.util.ThreadLocalUtil;
import com.jay.pmanage.util.encryptUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TenantsServiceImpl implements TenantsService {
    private final TenantsMapper tenantsMapper;
    private final PropertiesMapper propertiesMapper;

    public TenantsServiceImpl(TenantsMapper tenantsMapper ,PropertiesMapper propertiesMapper)
    {
        this.tenantsMapper = tenantsMapper;
        this.propertiesMapper = propertiesMapper;
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
        Properties properties = propertiesMapper.findPropertyByAddress(tenants.getAddress());
        if(properties != null)
        {
            tenants.setPropertyId(properties.getId());
        }
        tenantsMapper.add(tenants);
    }

    @Override
    public void deleteTenants(Integer tenantId)
    {
        tenantsMapper.delete(tenantId);
    }

    @Override
    public Tenants getTenantById(Integer tenantId) {
        return tenantsMapper.getTenantById(tenantId);
    }

    @Override
    public Tenants modifyTenants(Integer tenantId,Tenants tenants) {
        Properties properties = propertiesMapper.findPropertyByAddress(tenants.getAddress());
        if(properties != null)
        {
            tenants.setPropertyId(properties.getId());
        }
        tenantsMapper.modify(tenantId,tenants);
        return tenantsMapper.getTenantById(tenantId);
    }

    @Override
    public List<Tenants> getTenantsByPropertyId(Integer propertyId) {
        return tenantsMapper.getTenantsByPropertyId(propertyId);
    }

    @Override
    public Tenants findTenantByName(String tenantName) {
        return tenantsMapper.findTenantByname(tenantName);
    }

}
