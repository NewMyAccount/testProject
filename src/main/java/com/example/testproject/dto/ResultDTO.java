package com.example.testproject.dto;

import com.example.testproject.exception.CustomException;
import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/8/25
 * @Time: 17:42
 **/
@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errorOf(CustomException ex) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ex.getCode());
        resultDTO.setMessage(ex.getMessage());
        return resultDTO;
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static <T> ResultDTO okOf(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }

}
