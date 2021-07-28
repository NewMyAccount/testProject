package com.example.testproject.controller;

import com.example.testproject.mapper.UserMapper;
import com.example.testproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 张昕
 * @Date： 2021/7/15
 * @Time: 14:46
 **/

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "index";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                //用传到服务器端的reqeust.cookie获取token，然后查询数据库校验session是否存在
                User user = userMapper.findByToken(token);
                if (user != null) {
                    //把user对象写入session
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

}
