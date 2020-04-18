package com.chj.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.SimpleTimeZone;

/**
 * @author ：chj
 * @date ：Created in 2020/4/17 11:00
 * @params :
 */
@SpringBootApplication
public class ShiroConfig {

    /**
     * 自定义shiro加密方式
     */
   @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
  /*  加密密码
    public static void main(String[] args) {
        SimpleHash simpleHash = new SimpleHash("MD5", "123456", "wangwu", 1024);
        System.out.println(simpleHash);
    }
*/
    /**
     * 定义自己的Realm
     * @return
     */
    @Bean(name = "shiroRealm")
   public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
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
