package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.Properties;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface PropertiesMapper {
    @Insert("INSERT INTO properties(address,state,price,propertytype,ownerid) VALUES (#{address},#{state},#{price},#{propertyType},#{ownerId})")
    void add(Properties properties);
}
