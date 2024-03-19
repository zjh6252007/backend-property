package com.jay.pmanage.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JwtUtil {
    private static final String KEY = "zhuzhu";

    public static String createJWT(){
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 24*3600*1000))
                .sign(Algorithm.HMAC256(KEY));
    }
}
