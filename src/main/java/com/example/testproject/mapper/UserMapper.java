package com.example.testproject.mapper;

import com.example.testproject.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @Author: 张昕
 * @Date： 2021/7/26
 * @Time: 15:46
 **/
@Mapper
public interface UserMapper {
    @Insert("insert into user (name, account_id, token, gmt_create,gmt_modified, avatar_url) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param(value = "token") String token);

    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    @Update("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where id = #{id}")
    void updateUser(User user);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(String accountId);
}
