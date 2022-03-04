package com.example.humanresources.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.humanresources.exception.ErrorCodeEnum;
import com.example.humanresources.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final long EXPIRE = 3600 * 100000;
    private static final String SECURITY = "!@#$%H*&^SxJ";

    /**
     * 生成token
     *
     * @return
     */
    public static String toToken(String username, String JSessionId) {
        JWTCreator.Builder builder = JWT.create();

        //设置头部
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS256");
        headers.put("typ", "JWT");
        builder.withHeader(headers);

        //设置载荷
        //token唯一表示（使用sessionId）
        builder.withJWTId(JSessionId);
        //username
        builder.withClaim("username", username);
        //过期时间
        builder.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE));

        //签名，生成token
        return builder.sign(Algorithm.HMAC256(SECURITY));
    }


    /**
     * 验签
     */
    public static boolean parseToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECURITY)).build();
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static void verify(String token) {
        // 如果验证通过，则不会把报错，否则会报错
        JWT.require(Algorithm.HMAC256(SECURITY)).build().verify(token);
    }

    /**
     * 解析数据
     */
    public static DecodedJWT parseData(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECURITY)).build();
        return jwtVerifier.verify(token);
    }

    /**
     * 获取token中包含的username
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 从请求头中获取token,然后获取token中的username
     */
    public static String getUsernameByToken(HttpServletRequest request) {
        String accessToken = request.getHeader("token");
        String username = getUsername(accessToken);
        if (username == null) {
            throw new ServiceException(ErrorCodeEnum.USER_USERNAME_NOT_ACCESS);
        }
        return username;
    }

}
