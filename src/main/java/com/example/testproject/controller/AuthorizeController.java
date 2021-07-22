package com.example.testproject.controller;

import com.example.testproject.dto.AccessTokenDTO;
import com.example.testproject.dto.GithubUser;
import com.example.testproject.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(rediretUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
//        System.out.println(user.getName());
//        System.out.println(user.getId());
        if (user != null) {
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

}
