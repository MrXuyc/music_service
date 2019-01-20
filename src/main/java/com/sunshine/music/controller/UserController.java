package com.sunshine.music.controller;

import com.github.pagehelper.PageHelper;
import com.sunshine.music.common.Const;
import com.sunshine.music.entity.User;
import com.sunshine.music.service.UserService;
import com.sunshine.music.util.Result;
import com.sunshine.music.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;
    @GetMapping("/user/query/{id}")
    public Result queryUser(@PathVariable(value = "id") Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/user/delete/{id}")
    public Result  deleteUser(@PathVariable(value = "id") Integer id){
         userService.deleteUser(id);
         return ResultUtil.success("1");
    }

    @GetMapping("/user/query/{name}")
    public Result queryUserByName(@PathVariable(value = "name") String name){
        User user = userService.findUserByName(name);
        return ResultUtil.success(user);
    }

    @PostMapping("/user/offline/{id}")
    public Result offLineUser(@PathVariable(value = "id") Integer id){

        return ResultUtil.success();
    }

    @PostMapping("/user/register")
    public Result saveUser(  @Valid User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtil.error("-2",bindingResult.getFieldError().getDefaultMessage());
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(Const.Status.onLine.getCode());
        userService.saveUser(user);
        return ResultUtil.success(user);
    }
}
