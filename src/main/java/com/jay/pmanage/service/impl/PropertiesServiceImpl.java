package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.PropertiesMapper;
import com.jay.pmanage.mapper.TenantsMapper;
import com.jay.pmanage.mapper.UserMapper;
import com.jay.pmanage.pojo.Properties;
import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.service.PropertiesService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class PropertiesServiceImpl implements PropertiesService {
    private final PropertiesMapper propertiesMapper;
    private final UserMapper userMapper;
    private final TenantsMapper tenantsMapper;
    PropertiesServiceImpl(PropertiesMapper propertiesMapper,UserMapper userMapper,TenantsMapper tenantsMapper){
        this.propertiesMapper = propertiesMapper;
        this.userMapper = userMapper;
        this.tenantsMapper = tenantsMapper;
    }

    @Override
    public List<Properties> getAll(Integer id) {
        return propertiesMapper.getAll(id);
    }

    @Override
    public void addProperties(Properties properties) {
        Map<String,Object> propertyMap = ThreadLocalUtil.get();
        Integer userid = (Integer) propertyMap.get("id");
        properties.setOwnerId(userid);
        propertiesMapper.add(properties);
    }

    @Override
    public void deleteProperties(Integer id) {
        propertiesMapper.delete(id);
    }

    @Override
    public void modifyProperties(Integer id, Properties properties) {
        propertiesMapper.modify(id,properties);
    }

    @Override
    public Properties findPropertyById(Integer id) {
        return propertiesMapper.findPropertyById(id);
    }

    @Override
    public Properties findTenantProperty() {
        Map<String,Object> userMap = ThreadLocalUtil.get();
        Integer userid = (Integer) userMap.get("id");
        Integer tenantId = userMapper.getTenantId(userid);
        Integer propertyId = tenantsMapper.getPropertyById(tenantId);
        return propertiesMapper.findPropertyById(propertyId);
    }

}
