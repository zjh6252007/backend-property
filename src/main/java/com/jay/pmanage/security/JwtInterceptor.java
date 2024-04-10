package com.jay.pmanage.security;

import com.jay.pmanage.util.JwtUtil;
import com.jay.pmanage.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;
    JwtInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if(token.startsWith("Bearer ")) {
            token = token.substring(7); //remove bearer
        }
        try{
            ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
            String jwtRedis = valueOperations.get(token);
            if(jwtRedis==null) // if the jwt token in redis is expired then response 401 error
            {
                throw new RuntimeException();
            }
            Map<String,Object> claims = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(claims); //store the information from jwt into thread-local
            return true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.clear();
    }
}
