package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.RepairRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Mapper
public interface RepairRequestMapper {
    @Insert("INSERT INTO repairrequests (property_id, tenant_id, description, status, available, created_at, updated_at) " +
            "VALUES (#{propertyId}, #{tenantId}, #{description}, " +
            "#{status},#{available}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(RepairRequest repairRequest);

    @Select("SELECT rr.* FROM repairrequests rr JOIN properties p ON rr.property_id = p.id WHERE p.ownerid=#{userid}")
    List<RepairRequest> getAll(Integer userid);
}
