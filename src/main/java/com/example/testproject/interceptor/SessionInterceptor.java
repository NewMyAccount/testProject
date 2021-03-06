package com.example.testproject.interceptor;

import com.example.testproject.mapper.UserDynamicSqlSupport;
import com.example.testproject.mapper.UserMapper;
import com.example.testproject.model.User;
import com.example.testproject.service.NotificationService;
import org.jetbrains.annotations.NotNull;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

/**
 * @Author: 张昕
 * @Date： 2021/8/13
 * @Time: 15:09
 **/

@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationService notificationService;

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //用传到服务器端的request.cookie获取token，然后查询数据库校验session是否存在
                    SelectStatementProvider selectStatement = select(UserDynamicSqlSupport.user.allColumns())
                            .from(UserDynamicSqlSupport.user)
                            .where(UserDynamicSqlSupport.token, isEqualTo(token))
                            .build().render(RenderingStrategies.MYBATIS3);
                    Optional<User> user = userMapper.selectOne(selectStatement);
                    if (user.isPresent()) {
                        //把user对象写入session
                        request.getSession().setAttribute("user", user.get());

                        request.getSession().setAttribute("unreadCount", notificationService.unreadCount(user.get()));
                        break;
                    }
                }
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
