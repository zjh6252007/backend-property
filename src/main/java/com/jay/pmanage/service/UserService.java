package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;

public interface UserService {
    User findUserByName(String username);
    void register(User user);
    Boolean login(String username, String password);
    String generateJWT(Integer id, String username);
    Boolean changePassword(String currentPwd, String newPwd);
    Boolean verifyEmail(String token);
}
