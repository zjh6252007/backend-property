package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.util.ThreadLocalUtil;
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
    public Result<String> login(@RequestBody Map<String,String> params)
    {
        String username = params.get("username");
        String password = params.get("password");
        User foundUser = userService.findUserByName(username);
        if(foundUser == null)
        {
            return Result.error("Can't find username");
        }else{
            if(userService.login(username,password)){
                return Result.success(userService.generateJWT(foundUser.getId(), username));
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
        user.setPassword(null);
        return Result.success(user);
    }

    @PatchMapping("/changePwd")
    public Result<Void> changePassword(@RequestBody Map<String,String> params)
    {

        String currentPassword = params.get("current_pwd");
        String newPassword = params.get("new_pwd");


        if(userService.changePassword(currentPassword,newPassword))
        {
            return Result.success();
        }else{
            return Result.error("Wrong Password");
        }
    }
}
