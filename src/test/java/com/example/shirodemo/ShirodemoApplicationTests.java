package com.example.shirodemo;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.UserService;
import com.example.shirodemo.utils.PinYinUtil;
import com.example.shirodemo.vo.DataUserVO;
import com.example.shirodemo.vo.DataVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShirodemoApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {

        User user = userService.getById(1);
        System.out.println(user);

    }

    @Test
    void findUserByusername() {
        String usename="sunhongyu";
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("username",usename);
        User user = userService.getOne(wrapper);
        System.out.println(user);

    }
    @Test
    void findAll(){
        DataVO<DataUserVO> data = userService.findData(1, 3);

        System.out.println(data);
    }

    @Test
    void TestAddUser(){

        String usercode="小明";
        String username= PinYinUtil.getPinyin(usercode);
        String email="xiaom@163.com";
        String password="Avaya123";
        String phone="18612345678";
        String perms="user:add";
        String role="user";
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
    }

}
