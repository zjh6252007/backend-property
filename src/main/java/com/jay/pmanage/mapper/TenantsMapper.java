package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.Tenants;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TenantsMapper {
    @Insert("INSERT INTO tenants(firstName,lastName,email,phone,address,create_user) VALUES(#{firstName},#{lastName},#{email},#{phone},#{address},#{createUser})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Tenants tenants);

    @Select("SELECT * FROM tenants WHERE create_user=#{userid}")
    List<Tenants> findAll(Integer userid);

    @Delete("DELETE FROM tenants WHERE id=#{tenantid}")
    void delete(Integer tenantid);
}
