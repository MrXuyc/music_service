package com.sunshine.music.service;

import com.sunshine.music.common.Const;
import com.sunshine.music.dao.UserCustomRepository;
import com.sunshine.music.dao.UserRepository;
import com.sunshine.music.entity.User;
import com.sunshine.music.util.Md5Util;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Date;


@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private UserCustomRepository customRepository;

    public User getUserById(Integer id){
        User user = userRepository.findOne(id);
        return user;
    }

    @Transactional
    public boolean saveUser(User user){
        //验证该手机号是否已经注册了
        if(checkSamePhone(user.getPhone())){
            //md5加密密码
            String encodePassword = Md5Util.MD5EncodeUtf8(user.getPassword());
            user.setPassword(encodePassword);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setStatus(Const.Status.onLine.getCode());
            userRepository.save(user);
            return true;
        }else {
            return false;
        }
    }

    public void onLineUser(Integer id) {
        User user = userRepository.findOne(id);
        user.setUpdateTime(new Date());
        user.setStatus(Const.Status.onLine.getCode());
        userRepository.save(user);
    }

    public void offLineUser(Integer id) {
        User user = userRepository.findOne(id);
        user.setUpdateTime(new Date());
        user.setStatus(Const.Status.offLine.getCode());
        userRepository.save(user);
    }

    public boolean checkSamePhone(String phone) {
        //查询是否有该账号
        User user = userRepository.findByPhone(phone);
        return user == null;
    }

    public boolean login(String phone, String password) {
        User dbUser = userRepository.findByPhone(phone);
        if(dbUser==null){
            return false;
        }
        //判断是否相等
        String md5Password = Md5Util.MD5EncodeUtf8(password);
        return dbUser.getPassword().equals(md5Password);
    }
}
