package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Properties;

import java.util.List;

public interface PropertiesService {
    List<Properties> getAll(Integer id);
    void addProperties(Properties properties);
    void deleteProperties(Integer id);
    void modifyProperties(Integer id, Properties properties);
    Properties findPropertyById(Integer id);
    Properties findTenantProperty();

}
