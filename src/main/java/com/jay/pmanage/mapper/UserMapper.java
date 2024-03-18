package com.jay.pmanage.mapper;

import com.jay.pmanage.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username=#{username}")
    User findUserByName(String username);

    @Insert("INSERT INTO user(username,password,email,salt) VALUES(#{username},#{password},#{email},#{salt})")
    void add(String username,String password,String email,String salt);
}
