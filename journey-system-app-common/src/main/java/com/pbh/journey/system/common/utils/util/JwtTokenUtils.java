package com.pbh.journey.system.common.utils.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author pangbohuan
 * @description JWT工具类
 * @date 2018-09-05 09:36
 **/
public class JwtTokenUtils {

    private static final String SECRET = "jwtSecret";
    private static final String ISS = "echisan";


    /**
     * 过期时间是2个小时
     */
    protected static final long EXPIRATION = 1000 * 60 * 60 * 2;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    protected static final long EXPIRATION_REMEMBER = 604800L * 1000;

    /**
     * 创建token
     */
    public static String createToken(String userAccount, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(userAccount)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    /**
     * 从token中获取用户名
     */
    public static String getUserAccount(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 获取token体
     */
    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
