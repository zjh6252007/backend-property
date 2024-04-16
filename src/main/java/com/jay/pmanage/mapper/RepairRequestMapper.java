package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.RepairRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;


@Mapper
public interface RepairRequestMapper {
    @Insert("INSERT INTO repairrequests (property_id, tenant_id, description, status, created_at, updated_at) " +
            "VALUES (#{propertyId}, #{tenantId}, #{description}, " +
            "#{status}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(RepairRequest repairRequest);
}
