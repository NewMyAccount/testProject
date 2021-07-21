package com.example.testproject.dto;

/**
 * @Author: 张昕
 * @Date： 2021/7/21
 * @Time: 11:19
 **/

public class GithubUser {
    private String name;
    private String id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
