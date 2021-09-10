package com.example.testproject.dto;

import com.example.testproject.model.User;
import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/9/1
 * @Time: 18:25
 **/
@Data
public class CommentDTO {
    private Integer id;

    private Integer parentId;

    private Integer type;

    private Integer commenter;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer likeCount;

    private Integer commentCount;

    private String content;

    private User user;
}
