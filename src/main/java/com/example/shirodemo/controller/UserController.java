package com.example.shirodemo.controller;

import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.UserService;
import com.example.shirodemo.utils.PinYinUtil;
import com.example.shirodemo.vo.DataVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/useradd")
    @ResponseBody
    public String useradd(String usercode,String email,String password,String phone,String role){
        System.out.println("abcd"+usercode+email);

        String username= PinYinUtil.getPinyin(usercode);
        String perms="user:view";
        String status="1";
        User user = new User();
        user.setUsercode(usercode);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setPerms(perms);
        user.setRole(role);
        user.setStatus(status);
        boolean b = userService.save(user);
        System.out.println(b);
        return "success";
    }

    @RequestMapping("/userdel")
    @ResponseBody
    public String userdel(@RequestBody Map<String, String> map){
       // if(map.containsKey("id")){
            Integer id = Integer.parseInt(map.get("id"));
            User user = userService.getById(id);
            System.out.println(user);
            boolean b = userService.removeById(id);
            System.out.println(b);
        //}



        return "success";

    }


    @RequestMapping("/userlist")
    @ResponseBody
    public DataVO userlist(Integer page, Integer limit){
        return userService.findData(page,limit);
    }




}
