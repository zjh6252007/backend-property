package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username=#{username}")
    User findUserByName(String username);

    @Select("SELECT * FROM tenants WHERE username=#{username}")
    Tenants findTenantsAccount(String username);
    @Insert("INSERT INTO user(username,password,email,salt,email_verification_token,email_verified) VALUES(#{username},#{password},#{email},#{salt},#{emailVerificationToken},#{emailVerified})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(User user);
    @Update("UPDATE user SET password=#{password}, salt=#{salt} WHERE id=#{userid}")
    void updatePassword(String password,Integer userid,String salt);

    @Select("SELECT * FROM user WHERE email_verification_token = #{token}")
    User findByVerificationToken(String token);

    @Update("UPDATE user SET email_verified =#{emailVerified} WHERE id =#{id}")
    void updateEmailVerified(@Param("id")Integer id,@Param("emailVerified") Boolean emailVerified);
}
