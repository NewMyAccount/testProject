package com.example.testproject.enums;

/**
 * @Author: 张昕
 * @Date： 2021/11/3
 * @Time: 14:52
 **/

public enum NotifyStatusEnum {
    UNREAD(0),
    READ(1);
    private int status;

    NotifyStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
