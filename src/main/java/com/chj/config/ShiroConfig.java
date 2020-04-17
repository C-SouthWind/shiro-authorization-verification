package com.chj.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * @author ：chj
 * @date ：Created in 2020/4/17 11:00
 * @params :
 */
@SpringBootApplication
public class ShiroConfig {
    /**
     * 定义自己的Realm
     * @return
     */
    @Bean(name = "shiroRealm")
   public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

    /**
     * 交给spring管理
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm());
        return defaultWebSecurityManager;
    }

    /**
     * 配置拦截
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        Map<String,String> filter = new LinkedMap();
        filter.put("logout","logout");

       /* 基于角色的权限控制
        filter.put("/user/add", "roles[buyer]");
        filter.put("/user/update", "roles[salesman]");
        filter.put("/user/add", "roles[buyer]");

        基于资源的权限控制
        filter.put("/user/add","perms[user:add]");
        filter.put("/user/update","perms[user:update]");
        filter.put("/user/delete","perms[user:delete]");*/

        filter.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filter);
        return shiroFilterFactoryBean;
    }
    /**
     * 配置thymeleaf和shiro标签配合使用
     */
    @Bean()
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
