package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.RepairRequest;
import com.jay.pmanage.pojo.RepairRequestDto;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Mapper
public interface RepairRequestMapper {
    @Insert("INSERT INTO repairrequests (property_id, tenant_id, description, status, available, created_at, updated_at) " +
            "VALUES (#{propertyId}, #{tenantId}, #{description}, " +
            "#{status},#{available}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(RepairRequest repairRequest);

    @Select("SELECT rr.id,p.address AS propertyAddress, " +
            "CONCAT(t.firstName, ' ', t.lastName) AS tenantName," +
            "t.phone AS tenantPhone,rr.description, rr.status, rr.available, rr.created_at AS createdAt, rr.updated_at AS updatedAt " +
            "FROM repairrequests rr " +
            "JOIN properties p ON rr.property_id = p.id " +
            "JOIN tenants t ON rr.tenant_id = t.id " +
            "WHERE p.ownerid = #{userId}")
    List<RepairRequestDto> getAll(Integer userid);

    @Select("SELECT * FROM repairrequests WHERE tenant_id = #{tenantId}")
    List<RepairRequest> getRepirBytenantId(Integer tenantId);

    @Update("UPDATE repairrequests SET status=#{status} WHERE id=#{id}")
    void updateStatus(@Param("status")String status,@Param("id")Integer id);
}
