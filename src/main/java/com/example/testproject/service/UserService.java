package com.example.testproject.service;

import com.example.testproject.mapper.UserMapper;
import com.example.testproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 张昕
 * @Date： 2021/8/17
 * @Time: 16:05
 **/
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        User foundUser = userMapper.findByAccountId(user.getAccountId());
        if (foundUser != null) {
            foundUser.setToken(user.getToken());
            foundUser.setAvatarUrl(user.getAvatarUrl());
            foundUser.setName(user.getName());
            foundUser.setGmtModified(System.currentTimeMillis());
            userMapper.updateUser(foundUser);
        } else {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
    }
}
