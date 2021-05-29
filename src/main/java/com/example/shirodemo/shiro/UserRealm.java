package com.example.shirodemo.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    /*
    *  执行授权逻辑
    *
    */
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加授权字段
        //info.addStringPermission("user:add");//Demo方式字段
        //数据库获取
        //获取用户id
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
       // User user1= (User) principalCollection.getPrimaryPrincipal();
       // System.out.println("对象为:"+user1);
        System.out.println("当前用户为"+user);

        info.addRole(user.getRole());
        //info.addStringPermission(user.getPerms());

        System.out.println(user.getPerms());
        System.out.println(user.getRole());

        return info;
    }
    /*
     *  执行认证逻辑
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设用户数据库
        //String username="sunhongyu";
        //String password="Avaya123";

        //shiro认证逻辑
        //判断用户名字
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;

        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("username",token.getUsername());
        User user = userService.getOne(wrapper);

        if (user==null){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
