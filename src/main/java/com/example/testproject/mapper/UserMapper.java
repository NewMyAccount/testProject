package com.example.testproject.mapper;

import com.example.testproject.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 张昕
 * @Date： 2021/7/26
 * @Time: 15:46
 **/
@Mapper
public interface UserMapper {
    @Insert("insert into user (name, account_id, token, gmt_create,gmt_modified) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);
}
