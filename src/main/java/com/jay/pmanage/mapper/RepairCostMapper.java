package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.RepairCost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RepairCostMapper {
    @Insert("INSERT INTO repaircosts(create_user,property_id,description,cost,date) VALUES (#{createUser},#{propertyId},#{description},#{cost},#{date})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(RepairCost repairCost);

    @Select("SELECT * FROM repaircosts WHERE property_id=#{propertyId}")
    List<RepairCost> getByPropertyId(Integer property_id);

    @Select("SELECT * FROM repaircosts WHERE create_user=#{userId}")
    List<RepairCost> getAll(Integer userId);

    @Delete("DELETE FROM repaircosts WHERE id=#{costId}")
    void delete(Integer costId);
}
