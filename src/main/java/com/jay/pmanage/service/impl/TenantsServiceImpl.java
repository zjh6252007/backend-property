package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.TenantsMapper;
import com.jay.pmanage.pojo.Tenants;
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
    private final EmailService emailService;
    public TenantsServiceImpl(TenantsMapper tenantsMapper ,EmailService emailService)
    {
        this.tenantsMapper = tenantsMapper;
        this.emailService = emailService;
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
        tenantsMapper.modify(tenantId,tenants);
        return tenantsMapper.getTenantById(tenantId);
    }

    @Override
    public void register(String username, String password,String token) {
        System.out.println("running");
        String salt = encryptUtil.generateSalt();
        String encryptPassword = encryptUtil.encodePassword(password,salt);
        tenantsMapper.register(username,encryptPassword,salt,true,token);
    }
}
