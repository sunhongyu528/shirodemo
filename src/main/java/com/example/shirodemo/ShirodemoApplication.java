package com.example.shirodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@SpringBootApplication
@EnableLdapRepositories
@MapperScan("com.example.shirodemo.mapper")
public class ShirodemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShirodemoApplication.class, args);
    }

}
