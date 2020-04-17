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
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/")
    public String Index(){
        return "index";
    }
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
    @GetMapping("/add")
    public String add(){
        return "useradd";
    }
}
