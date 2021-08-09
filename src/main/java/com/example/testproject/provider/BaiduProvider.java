package com.example.testproject.provider;

import com.alibaba.fastjson.JSON;
import com.example.testproject.dto.AccessTokenDTO;
import com.example.testproject.dto.BaiduTokenDTO;
import com.example.testproject.dto.BaiduUser;
import com.example.testproject.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: 张昕
 * @Date： 2021/8/9
 * @Time: 11:51
 **/

@Component
public class BaiduProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://openapi.baidu.com/oauth/2.0/token?grant_type=authorization_code&code=" + accessTokenDTO.getCode() +
                        "&client_id=" + accessTokenDTO.getClientId() + "&client_secret=" + accessTokenDTO.getClientSecret() + "&redirect_uri=" + accessTokenDTO.getRedirectUri())
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.code() == 404) {
                return null;
            }
            String string = response.body().string();
            BaiduTokenDTO baiduTokenDTO = JSON.parseObject(string, BaiduTokenDTO.class);
            System.out.println(string);
            return baiduTokenDTO.getAccessToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BaiduUser getUser(String baiduTokenDTO) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://pan.baidu.com/rest/2.0/xpan/nas?method=uinfo&access_token=" + baiduTokenDTO)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            BaiduUser baiduUser = JSON.parseObject(string, BaiduUser.class);
            return baiduUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
