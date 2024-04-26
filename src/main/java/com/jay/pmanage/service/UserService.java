package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.Tenants;
import com.jay.pmanage.pojo.User;

public interface UserService {
    User findUserByName(String username);
    Tenants findTenantsAccount(String username);
    void register(User user);
    void registerTenant(String username,String password,String token) throws Exception;
    Boolean login(String username, String password);
    String generateJWT(Integer id, String username,String role);
    Boolean changePassword(String currentPwd, String newPwd);
    Boolean verifyEmail(String token);
    void resendVerificationEmail() throws Exception;
    void sendInvitationEmail(Integer tenantsId) throws Exception;
}
