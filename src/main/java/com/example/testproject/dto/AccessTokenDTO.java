package com.example.testproject.dto;

import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/7/20
 * @Time: 17:20
 **/

@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
}
