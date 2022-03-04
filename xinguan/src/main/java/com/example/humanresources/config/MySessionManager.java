package com.example.humanresources.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.humanresources.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

@Slf4j
@Configuration
public class MySessionManager extends DefaultWebSessionManager {
    /**
     * 从请求中获取sessionId
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader("Authorization");

        if (token == null || token.trim().equals("") || !JwtUtil.parseToken(token)) {
            //按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        } else {
            //从token中获取sessionId
            DecodedJWT decodedJWT = JwtUtil.parseData(token);
            String id = decodedJWT.getId();

            //如果请求头中有 Authorization 则其值为sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "url");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }
    }
}
