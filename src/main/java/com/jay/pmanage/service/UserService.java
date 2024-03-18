package com.jay.pmanage.service;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;

public interface UserService {
    public User findUserByName(String username);
    public void register(String username, String password, String email);
}
