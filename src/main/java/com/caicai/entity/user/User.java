package com.caicai.entity.user;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.caicai.entity.BaseEntity;
import lombok.Data;


@Data
@TableName("user")
public class User extends BaseEntity {
    @TableId
    private String id;
    @TableField("userName")
    private String userName;
    @TableField("passWord")
    private String passWord;
    @TableField("phone")
    private String phone;
    @TableField("age")
    private String age;
    @TableField("address")
    private String address;
    @TableField("subscribe")
    private String subscribe;

}
