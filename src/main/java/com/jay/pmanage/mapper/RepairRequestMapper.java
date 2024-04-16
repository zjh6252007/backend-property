package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.RepairRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;


@Mapper
public interface RepairRequestMapper {
    @Insert("INSERT INTO repairrequests (property_id, tenant_id, description, status, created_at, updated_at) " +
            "VALUES (#{repairRequest.propertyId}, #{repairRequest.tenantId}, #{repairRequest.description}, " +
            "#{repairRequest.status}, #{repairRequest.createdAt}, #{repairRequest.updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "repairRequest.id")
    void create(RepairRequest repairRequest);
}
