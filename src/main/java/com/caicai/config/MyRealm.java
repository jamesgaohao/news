package com.caicai.config;

import com.caicai.dao.UserService;
import com.caicai.entity.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 实现授权逻辑
        // 获取当前用户信息
//        String username = (String) principals.getPrimaryPrincipal();

        // 返回用户的权限信息
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 实现身份验证逻辑
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        // 根据用户名和密码验证用户的身份
        User user = userService.getByName(username);
         //验证用户名和密码是否匹配
        if ( user== null || !user.getPassWord().equals(password)) {
            throw new IncorrectCredentialsException();
        }

        // 记录用户登录信息
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("user", user);
        Session session = subject.getSession();

        session.setAttribute("user", user);
        // 创建AuthenticationInfo对象，并设置用户的身份验证信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        // 如果验证成功，返回AuthenticationInfo对象，否则抛出AuthenticationException异常
        return authenticationInfo;
    }
}
