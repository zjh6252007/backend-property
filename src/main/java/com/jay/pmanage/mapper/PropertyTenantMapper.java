package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.PropertyTenant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PropertyTenantMapper {
    @Insert("INSERT INTO propertiestenants (property_id,tenant_id,lease_start_date," +
            "lease_end_date)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(PropertyTenant propertyTenant);

    @Select("SELECT * FROM propertiestenants WHERE property_id=#{property_id}")
    List<PropertyTenant> findByPropertyId(int propertyId);
}
