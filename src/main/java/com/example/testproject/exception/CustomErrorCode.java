package com.example.testproject.exception;

/**
 * @Author: 张昕
 * @Date： 2021/8/23
 * @Time: 18:45
 **/

public enum CustomErrorCode {

    QUESTION_NOT_FOUND("你找的问题不存在，换一个试试");

    private String message;

    CustomErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
