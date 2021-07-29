package com.example.testproject.model;

import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/7/26
 * @Time: 15:49
 **/

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
