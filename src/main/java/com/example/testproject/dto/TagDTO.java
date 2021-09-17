package com.example.testproject.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: 张昕
 * @Date： 2021/9/17
 * @Time: 13:42
 **/

@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
