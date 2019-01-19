package com.sunshine.basepro.dao;

import com.sunshine.basepro.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface CustomRepository extends Repository<User,Integer> {
    /**
     * 1 查询方法  以find  get  read 开头
     * 2 涉及条件查询时  条件的属性用条件关键字链接，注意条件属性属字母大写
     * @param name
     * @return
     */
    public User findByName(String name);

    /**
     * JPQL
     */
    @Query(value = "select u from User u where name =:nameparam")
    public User findByNameCustom(@Param(value = "nameparam") String name);

}
