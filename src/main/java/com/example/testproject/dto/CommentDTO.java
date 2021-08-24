package com.example.testproject.dto;

import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/8/24
 * @Time: 15:58
 **/
@Data
public class CommentDTO {
    private String content;
    private String parentId;
    private int type;
    private int commenter;
}
