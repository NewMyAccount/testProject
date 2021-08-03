package com.example.testproject.dto;

import com.example.testproject.model.User;
import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/8/3
 * @Time: 9:41
 **/
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
