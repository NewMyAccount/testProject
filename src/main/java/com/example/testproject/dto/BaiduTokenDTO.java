package com.example.testproject.dto;

import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/8/9
 * @Time: 14:47
 **/

@Data
public class BaiduTokenDTO {
    private String expiresIn;
    private String refreshToken;
    private String accessToken;
    private String sessionSecret;
    private String sessionKey;
    private String scope;
}
