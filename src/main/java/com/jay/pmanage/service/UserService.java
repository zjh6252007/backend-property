package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;

public interface UserService {
    public User findUserByName(String username);
    public void register(String username, String password, String email);

    public String getSalt(String username);
    public String getPassword(String username);

    public String generateJWT(Integer id, String username);
}
