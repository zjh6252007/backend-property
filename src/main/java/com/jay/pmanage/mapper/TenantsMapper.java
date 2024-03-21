package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.Tenants;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TenantsMapper {
    @Insert("INSERT INTO tenants(firstName,lastName,email,phone,address,create_user) VALUES(#{firstName},#{lastName},#{email},#{phone},#{address},#{createUser})")
    void add(Tenants tenants);
}
