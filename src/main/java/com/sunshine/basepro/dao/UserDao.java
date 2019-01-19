package com.sunshine.basepro.dao;

import com.sunshine.basepro.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public User selectUserByName(String name){
        String sql="select * from user where name=?";
        RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
        User user=jdbcTemplate.queryForObject(sql,new Object[]{name},rowMapper);
        return user;
    }
}
