package com.example.testproject.dto;

import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/7/20
 * @Time: 17:20
 **/

@Data
public class AccessTokenDTO {
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
}
