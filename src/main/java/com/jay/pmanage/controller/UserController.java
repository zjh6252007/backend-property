package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(String username,String password,String email)
    {
        System.out.println("called");
        User user = userService.findUserByName(username);
        if(user == null)
        {
            userService.register(username,password,email);
            return Result.success();
        }else{
            return Result.error("Username Existed");
        }
    }
}
