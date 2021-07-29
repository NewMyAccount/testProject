package com.example.testproject.dto;

import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/7/21
 * @Time: 11:19
 **/

@Data
public class GithubUser {
    private String name;
    private String id;
    private String bio;
    private String avatarUrl;
}
