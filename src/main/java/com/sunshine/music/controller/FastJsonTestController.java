package com.sunshine.music.controller;

import com.sunshine.music.entity.User;
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
        user.setName("xsdqwe");
        user.setCreateTime(new Date());
        return user;
    }
}
