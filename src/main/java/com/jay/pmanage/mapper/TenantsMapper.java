package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.Properties;
import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.pojo.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper
public interface TenantsMapper {
    @Select("SELECT * FROM tenants WHERE username=#{username}")
    Tenants findUserByName(String username);
    @Select("SELECT * FROM tenants WHERE username=#{username}")
    Tenants findTenantsAccount(String username);
    @Insert("INSERT INTO tenants(firstName,lastName,email,phone,address,create_user,property_id) VALUES(#{firstName},#{lastName},#{email},#{phone},#{address},#{createUser},#{propertyId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Tenants tenants);

    @Select("SELECT * FROM tenants WHERE create_user=#{userid}")
    List<Tenants> findAll(Integer userid);

    @Select("SELECT * FROM tenants WHERE id=#{tenantid}")
    Tenants getTenantById(Integer tenantid);
    @Delete("DELETE FROM tenants WHERE id=#{tenantid}")
    void delete(Integer tenantid);

    @Update("UPDATE tenants SET firstName=#{tenant.firstName}, lastName=#{tenant.lastName}, email=#{tenant.email}, " +
            "phone=#{tenant.phone}, address=#{tenant.address},property_id=#{tenant.propertyId} WHERE id=#{tenantid}")
    void modify(@Param("tenantid") Integer tenantid, @Param("tenant") Tenants tenants);

    @Update("UPDATE tenants SET invitation_token=#{invitationToken} WHERE id=#{tenantId}")
    void updateInvitationToken(@Param("tenantId")Integer tenantId, @Param("invitationToken")String invitationToken);

    @Select("SELECT * FROM tenants WHERE invitation_token = #{token}")
    Tenants findByInvitationToken(String token);
    @Select("SELECT * FROM tenants WHERE property_id=#{propertyId}")
    List<Tenants> getTenantsByPropertyId(Integer propertyId);

    @Select("SELECT * FROM tenants WHERE username=#{username}")
    Tenants findTenantByname(String tenantname);

    @Update("UPDATE tenants SET active=true WHERE id=#{id}")
    void activeTenants(Integer id);

    @Select("SELECT property_id FROM tenants WHERE id=#{id}")
    Integer getPropertyById(Integer id);

}
