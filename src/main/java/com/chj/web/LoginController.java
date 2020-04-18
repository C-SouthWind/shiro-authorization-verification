package com.chj.web;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：chj
 * @date ：Created in 2020/4/17 10:43
 * @params :
 */
@Controller
public class LoginController {
    /**
     * 路径
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 根目录
     * @return
     */
    @GetMapping("/")
    public String Index(){
        return "index";
    }

    /**
     * post请求
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String selectByName(String username ,String password){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
           try {
               subject.login(token);
               System.out.println("认证通过");
           }catch(UnknownAccountException uae){
               System.out.println("用户不存在"+token.getPrincipal());
           }catch (IncorrectCredentialsException ice){
               System.out.println("密码错误");
           }catch (LockedAccountException lae){
               System.out.println("账户被锁定");
           }catch (AuthenticationException ae){
               System.out.println("认证失败");
           }
        }
        return "login";
    }

    /**
     * 添加请求
     * @return
     */
    @GetMapping("/add")
    public String add(){
        return "useradd";
    }
}
