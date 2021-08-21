package com.example.testproject.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/7/20
 * @Time: 17:20
 **/

@Data
public class AccessTokenDTO {
    @JSONField(name = "client_id")
    private String clientId;
    @JSONField(name = "client_secret")
    private String clientSecret;
    private String code;
    @JSONField(name = "redirect_uri")
    private String redirectUri;
}
