package com.example.testproject.exception;

/**
 * @Author: 张昕
 * @Date： 2021/8/23
 * @Time: 18:45
 **/

public enum CustomErrorCode {

    QUESTION_NOT_FOUND(1, "你找的问题不存在，换一个试试"),
    TYPE_NOT_FOUND(2, "回复类型不明确"),
    USER_NOT_FOUND(3, "用户未登录"),
    CONTENT_IS_EMPTY(4, "评论内容为空"),
    COMMENT_NOT_FOUND(5, "该评论不存在")
    ;

    private final Integer code;
    private final String message;

    CustomErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
