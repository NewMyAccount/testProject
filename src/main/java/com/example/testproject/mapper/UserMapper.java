package com.example.testproject.mapper;

import com.example.testproject.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    User findByToken(@Param("token") String token);

    @Select("select * from user where account_id = #{accountId}")
    User findById(@Param("accountId") Integer accountId);
}
