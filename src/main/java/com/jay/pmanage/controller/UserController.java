package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;
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
    public Result register(@RequestBody UserRegistrationDto registrationDto)
    {
        User user = userService.findUserByName(registrationDto.getUsername());
        if(user == null)
        {
            userService.register(registrationDto.getUsername(),registrationDto.getPassword(), registrationDto.getEmail());
            return Result.success();
        }else{
            return Result.error("Username Existed");
        }
    }
}
