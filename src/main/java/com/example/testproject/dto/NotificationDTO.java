package com.example.testproject.dto;

import com.example.testproject.model.User;
import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/11/3
 * @Time: 15:31
 **/

@Data
public class NotificationDTO {
    private Integer id;
    private User notifier;
    private Integer status;
    private Long gmtCreate;
    private String type;
    private String title;
}
