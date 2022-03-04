package com.example.humanresources.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.humanresources.utils.JwtUtil;
import com.example.humanresources.utils.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        R r = new R();
        try {
            JwtUtil.parseData(token);
            return true;
        } catch (TokenExpiredException e) {
            r.setData("Token已经过期");
        } catch (SignatureVerificationException e) {
            r.setData("签名错误");
        } catch (AlgorithmMismatchException e) {
            r.setData("加密算法不匹配");
        } catch (Exception e) {
            e.printStackTrace();
            r.setData("无效token");
        }
        String json = new ObjectMapper().writeValueAsString(r);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
