package com.jay.pmanage.service.impl;

import com.jay.pmanage.mapper.TenantsMapper;
import com.jay.pmanage.mapper.UserMapper;
import com.jay.pmanage.pojo.Properties;
import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.service.EmailService;
import com.jay.pmanage.service.UserService;
import com.jay.pmanage.util.JwtUtil;
import com.jay.pmanage.util.ThreadLocalUtil;
import com.jay.pmanage.util.encryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final EmailService emailService;
    private final StringRedisTemplate redisTemplate;
    private final TenantsMapper tenantsMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper,EmailService emailService, StringRedisTemplate redisTemplate,TenantsMapper tenantsMapper){
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.redisTemplate = redisTemplate;
        this.tenantsMapper = tenantsMapper;
    }
    @Override
    public User findUserByName(String username) {

        return userMapper.findUserByName(username);

    }

    @Override
    public Tenants findTenantsAccount(String username) {
        return userMapper.findTenantsAccount(username);
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
        user.setRole("owner");
        userMapper.add(user);
        String verificationUrl = "localhost:3000/user" + "/verify-email?token="+verify_token;
        emailService.sendVerificationEmail(user.getEmail(), "Verify Your Email",verificationUrl);
    }

    @Override
    public void registerTenant(String username, String password,String token)throws Exception {
        Tenants tenant= tenantsMapper.findByInvitationToken(token);
        if(tenant.isActive()){
            throw new Exception("Account has been registered");
        }
        
        Integer id = tenant.getId();
        String salt = encryptUtil.generateSalt();
        String encryptPassword = encryptUtil.encodePassword(password,salt);
        userMapper.addTenantAccount(username,encryptPassword,salt,token,id,true,"tenant");
        tenantsMapper.activeTenants(id);
    }

    @Override
    public Boolean login(String username,String password) {
        User user = userMapper.findUserByName(username);
        String salt = user.getSalt();
        String encryptPassword = encryptUtil.encodePassword(password,salt);
        return encryptPassword.equals(user.getPassword());
    }

    @Override
    public String generateJWT(Integer id, String username,String role) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",id);
        claims.put("username",username);
        claims.put("role",role);
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

    @Override
    public void resendVerificationEmail() throws Exception{
        Map<String,Object> userMap = ThreadLocalUtil.get();
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        String username = userMap.get("username").toString();
        User user = userMapper.findUserByName(username);
        String email = user.getEmail();
        Long expireSeconds = redisTemplate.getExpire("resendToken:" + email, TimeUnit.SECONDS);
        if(expireSeconds != null && expireSeconds > 0){
            throw new Exception("Please wait "+expireSeconds+ "seconds before resending the email");
        }

        String verify_token = user.getEmailVerificationToken();
        String verificationUrl = "localhost:3000/user" + "/verify-email?token="+verify_token;
        emailService.sendVerificationEmail(email,"Verify Your Email",verificationUrl);
        ops.set("resendToken:"+email,"sent",1, TimeUnit.MINUTES);
    }

    @Override
    public void sendInvitationEmail(Integer tenantsId) throws Exception{
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        Tenants tenants = tenantsMapper.getTenantById(tenantsId);
        String email = tenants.getEmail();
        if(email == null){
            throw new Exception("Please Input Email Address First");
        }
        String address = tenants.getAddress();
        Long expireSeconds = redisTemplate.getExpire("invite:" + email, TimeUnit.SECONDS);
        if(expireSeconds != null && expireSeconds > 0)
        {
            throw new Exception("Please wait "+expireSeconds+" seconds before sending the link");
        }
        if(tenants.getInvitationToken() == null){
            String token = UUID.randomUUID().toString();
            tenantsMapper.updateInvitationToken(tenantsId,token);
            tenants.setInvitationToken(token);
        }
        String token = tenants.getInvitationToken();
        String link = "localhost:3000/register/tenant?invitation_token=" + token;
        emailService.sendInviteEmail(email,"Register your account at: "+address,link);
        ops.set("invite:"+email,"sent",5,TimeUnit.MINUTES);
    }

    @Override
    public Integer getPropertyIdByTenantId() {
        Map<String,Object> propertyMap = ThreadLocalUtil.get();
        Integer userId = (Integer) propertyMap.get("id");
        Integer tenantId = userMapper.getTenantId(userId);
        return tenantsMapper.getPropertyById(tenantId);
    }
}
