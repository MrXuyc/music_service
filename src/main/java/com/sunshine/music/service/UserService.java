package com.sunshine.music.service;

import com.sunshine.music.dao.CustomRepository;
import com.sunshine.music.dao.UserDao;
import com.sunshine.music.dao.UserRepository;
import com.sunshine.music.entity.User;
import com.sunshine.music.mapper.UserMapper;
import com.sunshine.music.util.Result;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private CustomRepository customRepository;
    @Resource
    private UserDao userDao;

    @Resource
    private UserMapper userMapper;


    public User getUserById(Integer id){
        return userRepository.findOne(id);
    }

    @Transactional
    public User addUser(User user){
        return userRepository.save(user);
    }
    @Transactional
    public User UpdateUser(User user){
        return userRepository.save(user);
    }
    @Transactional
    public void deleteUser(Integer id){
        userRepository.delete(id);
    }

    public User findUserByName(String name){
        return customRepository.findByNameCustom(name);
    }

    public User findUserByDaoName(String name){
        return userDao.selectUserByName(name);
    }

    @Transactional
    public void saveUser(User user){
        userMapper.save(user);
    }

    public List<User> findUserByNameMapper(String name){
        return userMapper.likeName(name);
    }

}
