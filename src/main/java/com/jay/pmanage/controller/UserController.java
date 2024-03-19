package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.util.encryptUtil;
import com.jay.pmanage.pojo.UserRegistrationDto;
import com.jay.pmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@RequestBody User user)
    {

        if(userService.findUserByName(user.getUsername()) == null)
        {
            userService.register(user.getUsername(),user.getPassword(), user.getEmail());
            return Result.success();
        }else{
            return Result.error("Username Existed");
        }
    }

    @PostMapping("/authorization")
    public Result<String> login(@RequestBody User user)
    {
        if(userService.findUserByName(user.getUsername()) == null)
        {
            return Result.error("Can't find username");
        }else{

            String salt = userService.getSalt(user.getUsername());
            String encrytPassword = encryptUtil.encodePassword(user.getPassword(),salt);
            if(encrytPassword.equals(userService.getPassword(user.getUsername()))){
                return Result.success("login success");
            }else{
                return Result.error("Wrong Password");
            }
        }
    }
}
