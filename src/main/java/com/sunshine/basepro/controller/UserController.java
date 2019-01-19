package com.sunshine.basepro.controller;

import com.github.pagehelper.PageHelper;
import com.sunshine.basepro.entity.User;
import com.sunshine.basepro.service.UserService;
import com.sunshine.basepro.util.Result;
import com.sunshine.basepro.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

@RestController
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 注入
     */
    @Value("${context}")
    private String context;

    @Resource
    private UserService userService;
    @GetMapping("/user/{id}")
    public User queryUser(@PathVariable(value = "id") Integer id){
        return userService.getUser(id);
    }
    @PostMapping("/user/add")
    //@Valid User user  ,BindingResult bindingResult  可以用实体类
    public Result addUser( @RequestParam(value = "name")  String name, @RequestParam(value="remark") String  remark,@RequestParam(value = "age")  Integer age){
        User user=new User();
        user.setName(name);
        user.setCreateTime(new Date());
        user.setRemark(remark);
        user.setAge(age);
        return ResultUtil.success(userService.addUser(user));
    }

    @GetMapping("/user/delete/{id}")
    public Result  deleteUser(@PathVariable(value = "id") Integer id){
         userService.deleteUser(id);
         return ResultUtil.success("1");
    }

    @GetMapping("/user/query/{name}")
    public Result queryUserByName(@PathVariable(value = "name") String name){
        PageHelper.startPage(1, 2);
        return ResultUtil.success(userService.findUserByNameMapper(name));
    }

    @GetMapping("/user/dao/query/{name}")
    public Result queryUserByNameDao(@PathVariable(value = "name") String name){
        return ResultUtil.success(userService.findUserByDaoName(name));
    }

    @PostMapping("/user/save")
    //@Valid User user,BindingResult bindingResult    可以用实体类
    public Result saveUser(  @Valid User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResultUtil.error("-2",bindingResult.getFieldError().getDefaultMessage());
        }
        //User user=new User();
        //user.setName(name);
        user.setCreateTime(new Date());
//        user.setRemark(remark);
//        user.setAge(age);
        userService.saveUser(user);
        return ResultUtil.success(user);
    }
}
