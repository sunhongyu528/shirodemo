package com.example.shirodemo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.shirodemo.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //创建shiro过滤工厂Bean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){

       //设置安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内在过滤器
        //shiro内内在过滤器，实现权限拦截

        /*
        常见过滤器
        1、anon： 无需认证
        2、authc：必须认证
        3、user： 如果使用rememberMe的功能，可以直接访问
        4、perms：该资源必须等等资源权限才可以使用
        5、roles：该怎样必须得到角色才可以使用
      */
        Map<String,String> filterMap= new LinkedHashMap<String,String>();
        //filterMap.put("/user/useradd","authc");
        //filterMap.put("/user/userupdate","authc");


        filterMap.put("/doc/**","authc");
        //filterMap.put("/user/login","anon");
        //filterMap.put("/user/logout","logout");
        //filterMap.put("/user/useradd","perms[user:add]");
        //filterMap.put("/user/userupdate","perms[user:update]");
        filterMap.put("/user/**","anon");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //调整登录界面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");


        return  shiroFilterFactoryBean;
    }


    //创建默认的Web SecurityManager
    @Bean(name ="securityManager" )
    public DefaultWebSecurityManager getDefWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm);

        return securityManager;
    }


    //创建Realm
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }




    //配置ShiroDialect，用于thymeleaf和shiro整合
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }




}
