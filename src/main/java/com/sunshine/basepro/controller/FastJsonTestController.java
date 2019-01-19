package com.sunshine.basepro.controller;

import com.sunshine.basepro.entity.Girl;
import com.sunshine.basepro.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class FastJsonTestController {

    @GetMapping(value = "/getUser")
    public User getUser(){
        User user=new User();
        user.setId(1);
        user.setName("徐彦春");
        user.setCreateTime(new Date());
        return user;
    }

    @GetMapping(value = "/getUserOne")
    public User getUserOne(){
        User user=new User();
        user.setId(1);
        user.setName("徐彦春，嘿哈");
        user.setCreateTime(new Date());
        return user;
    }

    @Resource
    private Girl girl;

    @GetMapping(value = "/getGirlOne")
    public Girl getGirlOne(){
        return girl ;
    }

}
