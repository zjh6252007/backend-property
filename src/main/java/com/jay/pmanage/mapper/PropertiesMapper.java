package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.Properties;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PropertiesMapper {
    @Select("SELECT * FROM properties WHERE ownerid=#{userid}")
    List<Properties> getAll(Integer userid);
    @Insert("INSERT INTO properties(address,state,price,propertytype,ownerid) VALUES (#{address},#{state},#{price},#{propertyType},#{ownerId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Properties properties);

    @Select("SELECT * FROM properties WHERE id=#{id}")
    Properties findPropertyById(Integer id);

    @Delete("DELETE FROM properties WHERE id=#{id}")
    void delete(Integer id);

    @Update("UPDATE properties SET state=#{properties.state},price=#{properties.price}," +
            "propertytype=#{properties.propertyType} WHERE id=#{id}")
    void modify(@Param("id") Integer id, @Param("properties")Properties properties);
}
