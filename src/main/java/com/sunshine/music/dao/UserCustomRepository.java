package com.sunshine.music.dao;

import com.sunshine.music.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserCustomRepository extends Repository<User,Integer> {
    /**
     * 1 查询方法  以find  get  read 开头
     * 2 涉及条件查询时  条件的属性用条件关键字链接，注意条件属性属字母大写
     */
    public User findByName(String name);

    @Query(value = "select u from User u where phone =:phone")
    public User findByPhone(@Param(value = "phone") String phone);
}
