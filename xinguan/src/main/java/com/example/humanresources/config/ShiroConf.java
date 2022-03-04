package com.example.humanresources.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConf {

    /**
     * 创建安全管理器
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm realm, DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        //会话管理
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * shiro核心过滤器    创建shiroFilter  //负责拦截所有请求
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置系统受限资源
        //配置系统公共资源
        Map<String, String> map = new HashMap<>();
        map.put("/tbUser/login", "anon");//anon  设置为公共资源  放行资源放在下面
//        map.put("/**", "authc");//authc  请求这个资源需要认证和授权
//
//        //默认认证界面路径---当认证不通过时跳转
//        shiroFilterFactoryBean.setLoginUrl("http://127.0.0.1:8081/#/login");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", new MyAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }

}
