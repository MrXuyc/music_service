package com.sunshine.basepro.service;

import com.sunshine.basepro.dao.CustomRepository;
import com.sunshine.basepro.dao.UserDao;
import com.sunshine.basepro.dao.UserRepository;
import com.sunshine.basepro.entity.User;
import com.sunshine.basepro.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
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


    public User getUser(Integer id){
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
