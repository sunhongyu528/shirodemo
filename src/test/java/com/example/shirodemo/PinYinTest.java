package com.example.shirodemo;

import com.example.shirodemo.utils.PinYinUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PinYinTest {
    @Test
    void test1(){
        String st = PinYinUtil.getPinyin("您好");
        System.out.println(st);
    }

}
