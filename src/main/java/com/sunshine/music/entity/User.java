package com.sunshine.music.entity;

import com.alibaba.fastjson.annotation.JSONField;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import java.util.Date;

import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "请填写用户名")
    private String name;

    @Length(min = 11, max = 11, message = "请填写正确手机号")
    @NotNull(message = "请填写手机号")
    private String phone;

    @Min(value = 0,message = "必须出生哦！")
    private Integer age;

    @NotNull(message = "请填写密码")
    private String password;

    @NotNull(message = "请填写电子邮箱，方便验证身份")
    private String email;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @JSONField(serialize = false)
    private int status;

}
