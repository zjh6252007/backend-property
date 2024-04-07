package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.util.ThreadLocalUtil;
import com.jay.pmanage.util.encryptUtil;
import com.jay.pmanage.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")

public class UserController {


    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/register")
    public Result<Void> register(@RequestBody User user)
    {

        if(userService.findUserByName(user.getUsername()) == null)
        {
            userService.register(user);
            return Result.success();
        }else{
            return Result.error("Username Existed");
        }
    }

    @PostMapping("/authorization")
    public Result<String> login(@RequestBody User user)
    {
        User foundUser = userService.findUserByName(user.getUsername());
        if(foundUser == null)
        {
            return Result.error("Can't find username");
        }else{
            String salt = userService.getSalt(user.getUsername());
            String encrytPassword = encryptUtil.encodePassword(user.getPassword(),salt);
            if(encrytPassword.equals(userService.getPassword(user.getUsername()))){
                return Result.success(userService.generateJWT(foundUser.getId(), user.getUsername()));
            }else{
                return Result.error("Wrong Password");
            }
        }
    }

    @GetMapping("/profile")
    public Result<User> userinfo()
    {
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user = userService.findUserByName(username);
        return Result.success(user);
    }
}
