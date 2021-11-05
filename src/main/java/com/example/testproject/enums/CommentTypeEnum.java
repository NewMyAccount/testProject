package com.example.testproject.enums;

/**
 * @Author: 张昕
 * @Date： 2021/8/25
 * @Time: 16:53
 **/

public enum CommentTypeEnum {
    COMMENT_TYPE(1),
    REPLY_TYPE(2)
    ;
    private final Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static boolean isExist(Integer type){
        for(CommentTypeEnum typeEnum: CommentTypeEnum.values()){
            if(typeEnum.getType().equals(type)){
                return true;
            }
        }
        return false;
    }
}
