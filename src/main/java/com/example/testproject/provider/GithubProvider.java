package com.example.testproject.provider;

import com.alibaba.fastjson.JSON;
import com.example.testproject.dto.AccessTokenDTO;
import com.example.testproject.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * @Author: 张昕
 * @Date： 2021/7/20
 * @Time: 17:07
 **/

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json;charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.code() == 404) {
                return null;
            }
            //先用‘&’分割字符串，获取字符串数据第0个即为token=XX ，再用‘=’分割。
            return Objects.requireNonNull(response.body()).string().split("&")[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization", "token" + accessToken)
                .build();
//        Request request = new Request.Builder()
//                .url("https://api.github.com/user?access_token=" + accessToken)
//                .build();
        try {
            Response response = client.newCall(request).execute();
            return JSON.parseObject(Objects.requireNonNull(response.body()).string(), GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
