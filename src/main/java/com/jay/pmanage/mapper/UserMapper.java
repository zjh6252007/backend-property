package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username=#{username}")
    User findUserByName(String username);

    @Insert("INSERT INTO user(username,password,email,salt) VALUES(#{username},#{password},#{email},#{salt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(User user);

    @Select("SELECT salt FROM user WHERE username=#{username}")
    String getSalt(String username);

    @Select("SELECT password FROM user WHERE username=#{username}")
    String getPassword(String username);

    @Update("UPDATE user SET password=#{password}, salt=#{salt} WHERE id=#{userid}")
    void updatePassword(String password,Integer userid,String salt);
}
