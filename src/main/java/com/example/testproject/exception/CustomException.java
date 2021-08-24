package com.example.testproject.exception;

/**
 * @Author: 张昕
 * @Date： 2021/8/23
 * @Time: 18:28
 **/

public class CustomException extends RuntimeException{
    private String message;

    public CustomException(CustomErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
