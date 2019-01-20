package com.sunshine.music.controller;


import com.google.common.base.Strings;

import com.sunshine.music.common.Const;
import com.sunshine.music.entity.User;
import com.sunshine.music.service.UserService;
import com.sunshine.music.util.Result;
import com.sunshine.music.util.ResultUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 登陆账号
     */
    @PostMapping("/user/login")
    public Result login(String phone,String password, HttpSession session){
        try {
            boolean loginFlag = userService.login(phone, password);
            if(loginFlag){
                session.setAttribute(Const.CURRENT_USER, phone);
                return ResultUtil.success();
            }
        }catch (Exception e){
            return ResultUtil.error("登陆失败，请稍后再试");
        }
        return ResultUtil.error("登陆失败，请稍后再试");
    }

    /**
     * 根据id查询用户
     */
    @GetMapping("/user/query/id/{id}")
    public Result queryUser(@PathVariable(value = "id") Integer id, HttpSession session){
        String phone = (String) session.getAttribute(Const.CURRENT_USER);
        if(Strings.isNullOrEmpty(phone)){
            return ResultUtil.error("用户未登陆，需要强制登录");
        }
        User user = userService.getUserById(id);
        return ResultUtil.success(user);
    }

    /**
     * 验证手机号是否注册
     */
    @GetMapping("/user/query/phone/{phone}")
    public Result queryUserByPhone(@PathVariable(value = "phone") String phone){
        //
        boolean checkFlag = userService.checkSamePhone(phone);
        if(checkFlag){
            return ResultUtil.success();
        }
        return ResultUtil.error("该手机号已经被注册");
    }

    /**
     * 下线账号  关闭权限
     */
    @GetMapping("/user/offline/{id}")
    public Result offLineUser(@PathVariable(value = "id") Integer id){
        try{
            userService.offLineUser(id);
        }catch (Exception e){
            return ResultUtil.error("修改状态异常，请稍后再试");
        }
        return ResultUtil.success();
    }

    /**
     * 上线账号  打开权限
     */
    @GetMapping("/user/online/{id}")
    public Result onLineUser(@PathVariable(value = "id") Integer id){
        try{
            userService.onLineUser(id);
        }catch (Exception e){
            return ResultUtil.error("修改状态异常，请稍后再试");
        }
        return ResultUtil.success();
    }

    /**
     * 注册账号
     */
    @PostMapping("/user/register")
    public Result saveUser(@Valid User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            boolean saveFlag = userService.saveUser(user);
            if(saveFlag){
                return ResultUtil.success(user);
            }
        }catch (Exception e){
            return ResultUtil.error("注册账号失败，请稍后再试");
        }
        return ResultUtil.error("注册账号失败，请稍后再试");
    }
}
