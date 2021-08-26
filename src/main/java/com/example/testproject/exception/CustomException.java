package com.example.testproject.exception;

/**
 * @Author: 张昕
 * @Date： 2021/8/23
 * @Time: 18:28
 **/

public class CustomException extends RuntimeException{
    private final String message;
    private final Integer code;

    public CustomException(CustomErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
