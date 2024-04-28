package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.Contract;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContractMapper {
    @Insert("INSERT INTO contract(name,start_time,create_user,end_time,property_id) VALUES (#{name},#{startTime},#{createUser},#{endTime},#{propertyId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertContract(Contract contract);
}
