package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.UserMapper;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.service.UserService;
import com.jay.pmanage.util.JwtUtil;
import com.jay.pmanage.util.ThreadLocalUtil;
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
    public void register(User user){
        String password = user.getPassword();
        String salt = encryptUtil.generateSalt();
        String encryptPassword = encryptUtil.encodePassword(password,salt);
        user.setPassword(encryptPassword);
        user.setSalt(salt);
        userMapper.add(user);
    }

    @Override
    public Boolean login(String username,String password) {
        User user = userMapper.findUserByName(username);
        String salt = user.getSalt();
        String encryptPassword = encryptUtil.encodePassword(password,salt);
        return encryptPassword.equals(user.getPassword());
    }

    @Override
    public String generateJWT(Integer id, String username) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",id);
        claims.put("username",username);
        return JwtUtil.createJWT(claims);
    }

    @Override
    public Boolean changePassword(String currentPwd, String newPwd) {
        Map<String,Object> userMap = ThreadLocalUtil.get();
        String username = userMap.get("username").toString();
        User user = userMapper.findUserByName(username);
        String salt = user.getSalt();
        String encrytoPassword = encryptUtil.encodePassword(currentPwd,salt);
        if(!encrytoPassword.equals(user.getPassword())){
            return false;
        }else{
            String newSalt = encryptUtil.generateSalt();
            String encryptNewPassword = encryptUtil.encodePassword(newPwd,newSalt);
            userMapper.updatePassword(encryptNewPassword,user.getId(),newSalt);
            return true;
        }
    }

}
