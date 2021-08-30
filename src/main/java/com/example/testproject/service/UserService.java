package com.example.testproject.service;

import com.example.testproject.mapper.UserDynamicSqlSupport;
import com.example.testproject.mapper.UserMapper;
import com.example.testproject.model.User;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.testproject.mapper.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

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
        SelectStatementProvider selectStatementProvider  = select(UserDynamicSqlSupport.user.allColumns())
                .from(UserDynamicSqlSupport.user)
                .where(accountId, isEqualTo(user.getAccountId()))
                .build().render(RenderingStrategies.MYBATIS3);
        Optional<User> optionalUser = userMapper.selectOne(selectStatementProvider);
        if (optionalUser.isPresent()) {
            user.setGmtModified(System.currentTimeMillis());
            UpdateStatementProvider updateStatementProvider = update(UserDynamicSqlSupport.user)
                    .set(name).equalTo(user.getName())
                    .set(avatarUrl).equalTo(user.getAvatarUrl())
                    .set(bio).equalTo(user.getBio())
                    .set(gmtModified).equalTo(user.getGmtModified())
                    .set(token).equalTo(user.getToken())
                    .where(accountId, isEqualTo(user.getAccountId()))
                    .build().render(RenderingStrategies.MYBATIS3);
            userMapper.update(updateStatementProvider);
        } else {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
    }
}
