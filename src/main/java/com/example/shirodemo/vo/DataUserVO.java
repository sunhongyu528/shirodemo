package com.example.shirodemo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DataUserVO {
    private Integer id;

    private String username;

    private String usercode;

    private String role;

    private String phone;

    private String email;

    private String status;

    private Date createTime;

    private Date lastLogin;

    private Date updateTime;
}
