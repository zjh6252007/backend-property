package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.UserMapper;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.service.UserService;
import com.jay.pmanage.util.JwtUtil;
import com.jay.pmanage.util.encryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    @Override
    public User findUserByName(String username) {

        return userMapper.findUserByName(username);

    }

    @Override
    public void register(String username, String password, String email){
        String salt = encryptUtil.generateSalt();
        String encryptPassword = encryptUtil.encodePassword(password,salt);
        userMapper.add(username,encryptPassword,email,salt);
    }

    @Override
    public String getSalt(String username) {
        return userMapper.getSalt(username);
    }

    @Override
    public String getPassword(String username) {
        return userMapper.getPassword(username);
    }

    @Override
    public String generateJWT(Integer id, String username) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",id);
        claims.put("username",username);
        return JwtUtil.createJWT(claims);
    }

}
