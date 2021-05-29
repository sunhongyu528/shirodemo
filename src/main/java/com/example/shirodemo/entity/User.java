package com.example.shirodemo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 孙宏宇
 * @since 2021-01-25
 */
@Data
@TableName("m_user")
public class User {


    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String usercode;

    private String perms;

    private String role;

    private String phone;

    private String email;

    private String password;

    private String status;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT)
    private Date lastLogin;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
