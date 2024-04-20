package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.UserMapper;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.service.EmailService;
import com.jay.pmanage.service.UserService;
import com.jay.pmanage.util.JwtUtil;
import com.jay.pmanage.util.ThreadLocalUtil;
import com.jay.pmanage.util.encryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final EmailService emailService;
    @Autowired
    public UserServiceImpl(UserMapper userMapper,EmailService emailService){
        this.userMapper = userMapper;
        this.emailService = emailService;
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
        String verify_token = UUID.randomUUID().toString();
        user.setEmailVerificationToken(verify_token);
        user.setEmailVerified(false);
        user.setPassword(encryptPassword);
        user.setSalt(salt);
        userMapper.add(user);
        String verificationUrl = "localhost:3000/user" + "/verify-email?token="+verify_token;
        emailService.sendVerificationEmail(user.getEmail(), "Verify Your Email",verificationUrl);
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

    @Override
    public Boolean verifyEmail(String token) {
        User user = userMapper.findByVerificationToken(token);
        if(user!=null && !user.getEmailVerified()){
            user.setEmailVerified(true);
            userMapper.updateEmailVerified(user.getId(),true);
            return true;
        }
        return false;
    }

}
