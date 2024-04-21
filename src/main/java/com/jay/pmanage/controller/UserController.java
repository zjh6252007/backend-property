package com.jay.pmanage.controller;

import com.jay.pmanage.pojo.Result;
import com.jay.pmanage.pojo.User;
import com.jay.pmanage.util.ThreadLocalUtil;
import com.jay.pmanage.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")

public class UserController {


    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;
    public UserController(UserService userService,StringRedisTemplate stringRedisTemplate){

        this.userService = userService;
        this.stringRedisTemplate = stringRedisTemplate;
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
                String token = userService.generateJWT(foundUser.getId(), username); //generate JWT after login success
                ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
                valueOperations.set(token, token, 1, TimeUnit.DAYS); //set current JWT in redis
                String jwtRedis = valueOperations.get(token);
                return Result.success(token);
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
    public Result<Void> changePassword(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token)
    {

        String currentPassword = params.get("current_pwd");
        String newPassword = params.get("new_pwd");


        if(userService.changePassword(currentPassword,newPassword))
        {
            stringRedisTemplate.delete(token);
            return Result.success();
        }else{
            return Result.error("Wrong Password");
        }
    }

    @GetMapping("/verify-email")
    public Result<String> verifyEmail(@RequestParam String token){
        if(userService.verifyEmail(token)){
            return Result.success("Email verified success");
        }else{
            return Result.error("Email has already been verified.");
        }
    }

    @GetMapping("/resend-verification")
    public Result<String> resendVerifyEmail() {
        try {
            userService.resendVerificationEmail();
            return Result.success("Email sent success.");
        }catch (Exception e)
        {
            return Result.error(e.getMessage());
        }
    }
}
