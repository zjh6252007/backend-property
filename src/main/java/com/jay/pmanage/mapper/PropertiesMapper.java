package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.Properties;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PropertiesMapper {
    @Select("SELECT * FROM properties WHERE ownerid=#{userid}")
    List<Properties> getAll(Integer userid);
    @Insert("INSERT INTO properties(address,state,price,propertytype,ownerid) VALUES (#{address},#{state},#{price},#{propertyType},#{ownerId})")
    void add(Properties properties);
}
