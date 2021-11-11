package com.example.testproject.controller;

import com.example.testproject.dto.AccessTokenDTO;
import com.example.testproject.dto.BaiduUser;
import com.example.testproject.dto.GithubUser;
import com.example.testproject.model.User;
import com.example.testproject.provider.BaiduProvider;
import com.example.testproject.provider.GithubProvider;
import com.example.testproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
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
@Slf4j
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private BaiduProvider baiduProvider;
    @Value("${baidu.AppKey}")
    private String baiduAppKey;
    @Value("${baidu.SecretKey}")
    private String baiduSecretKey;
    @Value("${baidu.redirect.uri}")
    private String baiduRedirectUri;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClientId(clientId);
        accessTokenDTO.setClientSecret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirectUri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        if (accessToken == null) {
            return "redirect:/";
        }
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            //生成token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setBio(githubUser.getBio());
            //把session写到数据库中
            userService.createOrUpdate(user);
            //给前端页面返回token
            response.addCookie(new Cookie("token", token));
            //request.getSession().setAttribute("githubUser", githubUser);
            return "redirect:/";
        }
        log.error("callback get github error, {}", githubUser);
        return "redirect:/";
    }


    //http://developer.baidu.com/console#app/project(百度授权回调地址)
    @GetMapping("/baiducallback")
    public String baiduCallback(@RequestParam(name = "code") String code, HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClientId(baiduAppKey);
        accessTokenDTO.setClientSecret(baiduSecretKey);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirectUri(baiduRedirectUri);
        String accessToken = baiduProvider.getAccessToken(accessTokenDTO);
        BaiduUser baiduUser = baiduProvider.getUser(accessToken);
        if (baiduUser != null && baiduUser.getUk() != null) {
            User user = new User();
            //生成token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(baiduUser.getBaiduName());
            user.setAccountId(String.valueOf(baiduUser.getUk()));
            user.setAvatarUrl(baiduUser.getAvatarUrl());
            //把session写到数据库中
            userService.createOrUpdate(user);
            //给前端页面返回token
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        }
        System.out.println("accessToken:" + accessToken);
        System.out.println("baiduUser:" + baiduUser);
        return "redirect:/";
    }
}
