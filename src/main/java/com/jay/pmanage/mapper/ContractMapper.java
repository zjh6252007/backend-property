package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.Contract;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Mapper
public interface ContractMapper {
    @Insert("INSERT INTO contract(name,start_time,create_user,end_time,property_id) VALUES (#{name},#{startTime},#{createUser},#{endTime},#{propertyId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertContract(Contract contract);

    @Select("SELECT * FROM contract WHERE property_id =#{id}")
    List<Contract> findContractByPropertyId(Integer id);

    @Delete("DELETE FROM contract WHERE id=#{id}")
    void deleteContract(Integer id);

}
