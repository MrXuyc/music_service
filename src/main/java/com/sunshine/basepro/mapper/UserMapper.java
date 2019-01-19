package com.sunshine.basepro.mapper;

import com.sunshine.basepro.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    //#{name}:参数占位符
    @Select("select * from User where name=#{name}")
    public List<User> likeName(String name);


    @Select("select * from User where id = #{id}")
    public User getById(long id);

    @Select("select name from User where id = #{id}")
    public String getNameById(long id);

    /**
     * 保存数据.
     */
    @Insert("insert into User(name,create_time,age,remark) values(#{name},#{createTime},#{age},#{remark})")
    //设置  自增id   会立即返回id  及属性和字段对应关系
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    public void save(User user);

}
