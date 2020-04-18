package com.chj.config;

import com.chj.Service.UserService;
import com.chj.model.User;
import com.chj.model.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ：chj
 * @date ：Created in 2020/4/17 11:00
 * @params :
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Subject subject = SecurityUtils.getSubject();
        String principal = (String)subject.getPrincipal();
        List<UserVo> userVos = userService.selectRoleByUserName(principal);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for(UserVo userVo : userVos){
            simpleAuthorizationInfo.addStringPermission(userVo.getRolename());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String)token.getPrincipal();
        User user = userService.selectUserByName(principal);
        if (null == user) {
            throw new UnknownAccountException("此用户不存在" + principal);
        }
        if (Integer.valueOf(user.getState()) == 0) {
            throw new LockedAccountException("此用户被锁定"+principal);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),ByteSource.Util.bytes(user.getSalt()),this.getName());
        return info;
    }
}
