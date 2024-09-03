package com.example.sp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.sp.pojo.entity.User;
import com.example.sp.pojo.response.ReturnCode;
import com.example.sp.pojo.response.exception.CustomException;
import com.example.sp.pojo.response.exception.TokenException;

import java.util.Map;

public class JWTUtil {
    public static String generateToken(User user) {
        String token;
        try {
            token = JWT.create()
                    .withClaim("user", JsonUtil.toJson(user))
                    .sign(Algorithm.HMAC256("123456"));
        } catch (Exception e) {
            throw new CustomException(ReturnCode.USERNAME_OR_PASSWORD_ERROR);
        }
        return token;
    }

    public static Map<String, Claim> getClaims(String token) {
        if (token == null) {
            throw new TokenException(ReturnCode.INVALID_TOKEN,"The token is null");
        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("123456")).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaims();
        } catch (JWTVerificationException e) {
            throw new TokenException(ReturnCode.INVALID_TOKEN, e.getMessage());
        }
    }
//    public static DecodedJWT tokenVerifier(String token) {
//        return JWT.require(Algorithm.HMAC256("123456")).build().verify(token);
//    }
}
