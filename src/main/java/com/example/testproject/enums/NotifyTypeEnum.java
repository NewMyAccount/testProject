package com.example.testproject.enums;

/**
 * @Author: 张昕
 * @Date： 2021/11/3
 * @Time: 10:50
 **/

public enum NotifyTypeEnum {
    COMMENT_TYPE(1, "评论了问题"),
    REPLY_TYPE(2, "回复了评论");

    private String information;
    private Integer type;

    NotifyTypeEnum(Integer type, String information) {
        this.type = type;
        this.information = information;
    }

    public Integer getType() {
        return type;
    }
    public String getInformation(){
        return information;
    }
}
