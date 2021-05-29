package com.example.shirodemo.service;

import com.example.shirodemo.vo.DataADPersonVO;
import com.example.shirodemo.vo.DataVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdServiceTest {
    @Autowired
   private AdService adService;

    @Test
    void test1(){
        DataVO<DataADPersonVO> dataVO = adService.findlist();
        System.out.println(dataVO);

    }
}