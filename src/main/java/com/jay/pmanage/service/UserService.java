package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;

public interface UserService {
    User findUserByName(String username);
    void register(String username, String password, String email);

    String getSalt(String username);
    String getPassword(String username);

    String generateJWT(Integer id, String username);
}
