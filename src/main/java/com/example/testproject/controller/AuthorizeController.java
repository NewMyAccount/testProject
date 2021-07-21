package com.example.testproject.controller;

import com.example.testproject.dto.AccessTokenDTO;
import com.example.testproject.dto.GithubUser;
import com.example.testproject.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 张昕
 * @Date： 2021/7/20
 * @Time: 16:32
 **/


/*

1 Users are redirected to request their GitHub identity
2 Users are redirected back to your site by GitHub
3 Your app accesses the API with the user's access token*/
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("e6dedd08826359276765");
        accessTokenDTO.setClient_secret("01da5f1ddbe49015d577e17bec2c94fb7a8683e0");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        System.out.println(user.getId());
        return "index";
    }

}
