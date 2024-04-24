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

    @Select("SELECT * FROM properties WHERE address=#{address}")
    Properties findPropertyByAddress(String address);
    @Delete("DELETE FROM properties WHERE id=#{id}")
    void delete(Integer id);

    @Update("<script>" +
            "UPDATE properties" +
            "<set>" +
            "<if test='properties.state != null'>state=#{properties.state},</if>" +
            "<if test='properties.price != null'>price=#{properties.price},</if>" +
            "</set>" +
            "WHERE id=#{id}" +
            "</script>")
    void modify(@Param("id") Integer id, @Param("properties")Properties properties);
}
