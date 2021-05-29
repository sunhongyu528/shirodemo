package com.example.shirodemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Controller
public class HelloController {

    @Autowired(required=true)
    private JavaMailSender javaMailSender;


    @RequestMapping("/")
    public String hello(Model model){
        model.addAttribute("msg","更多内容请注册后登录");
        return "index";
    }


    @RequestMapping("/test")
    public String testThymleaf(Model model){

        return "test";
    }

    @RequestMapping("/admin")
    public String admin(){

        return "admin";
    }

    @RequestMapping("/toLogin")
    public String login(){


        return "index";
    }

    @RequestMapping("/logout")
    public String logout(Model model){
        model.addAttribute("msg","更多内容请注册后登录");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }

    @RequestMapping("/unAuth")
    public String unAuth(Model model) {
        model.addAttribute("msg","更多内容请注册后登录");
        return "index";
    }




    @RequestMapping("/login")
    public String userlogin(String username, String password, Model model){
        System.out.println(username);
        System.out.println(username);

        //shiro认证
        //1.获取Subject
        Subject subject= SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        //登录方法
        try {
            subject.login(token);
            model.addAttribute("username",username);
            return "forward:/admin";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            model.addAttribute("msg","用户名错误");
            return "forward:/toLogin";
        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("msg", "密码错误");
            return "forward:/toLogin";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg", "登录失败");
            return "forward:/toLogin";
        }

    }


    //发送普通文本邮件
    @RequestMapping(value = "/sendMail")
    public String sendMail(Model model,String mailco,String mailname,String mailtelephone,String mailadd) {


        String mail1="公司名称："+mailco+",联络人："+mailname+"，联系人手机："+mailtelephone+",联络人邮箱地址"+mailadd;
        String msg="";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sunhoyu@163.com"); //发送者
        message.setTo("wangxuefei@changhongit.com");  //接受者
        message.setBcc("sunhy@changhongit.com"); //抄送，填发送者的邮箱即可
        message.setCc("wangqian@changhongit.com"); //抄送，填发送者的邮箱即可
        message.setSubject("cpaas-poc网站测试申请");	//主题
        message.setText(mail1);	//内容
        try {
            javaMailSender.send(message);
            System.out.println("简单邮件已经发送");
            msg="邮件已经发送";
        } catch (Exception e) {
            System.out.println("发送简单邮件时发生异常！"+e.toString());
            msg="发送邮件时发生异常!";
        }
        model.addAttribute("msg", msg);
        return "index";
    }


}
