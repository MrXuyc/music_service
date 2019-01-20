package com.sunshine.music.mapper;

import com.sunshine.music.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
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
    @Insert("insert into User(name,create_time,age,status) values(#{name},#{createTime},#{age},#{status})")
    //设置  自增id   会立即返回id  及属性和字段对应关系
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    public void save(User user);

}
