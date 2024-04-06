package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.PropertiesMapper;
import com.jay.pmanage.pojo.Properties;
import com.jay.pmanage.service.PropertiesService;
import com.jay.pmanage.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class PropertiesServiceImpl implements PropertiesService {
    private final PropertiesMapper propertiesMapper;
    PropertiesServiceImpl(PropertiesMapper propertiesMapper){
        this.propertiesMapper = propertiesMapper;
    }
    @Override
    public void addProperties(Properties properties) {
        Map<String,Object> propertyMap = ThreadLocalUtil.get();
        Integer userid = (Integer) propertyMap.get("id");
        properties.setOwnerId(userid);
        propertiesMapper.add(properties);
    }
}
