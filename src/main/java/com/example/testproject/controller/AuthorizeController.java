package com.example.testproject.controller;

import com.example.testproject.dto.AccessTokenDTO;
import com.example.testproject.dto.GithubUser;
import com.example.testproject.mapper.UserMapper;
import com.example.testproject.model.User;
import com.example.testproject.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author: 张昕
 * @Date： 2021/7/20
 * @Time: 16:32
 **/

/*
1 Users are redirected to request their GitHub identity
2 Users are redirected back to your site by GitHub
3 Your app accesses the API with the user's access token
*/

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String rediretUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(rediretUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (accessToken != null && githubUser != null) {
            User user = new User();
            //生成token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            //把session写到数据库中
            userMapper.insert(user);
            //给前端页面返回token
            response.addCookie(new Cookie("token", token));
            //request.getSession().setAttribute("githubUser", githubUser);
            return "redirect:/";
        } else {
            System.out.println("accessToken:" + accessToken);
            System.out.println("githubUser:" + githubUser);
            return "redirect:/";
        }
    }

}
