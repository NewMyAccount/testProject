package com.example.testproject.dto;

import com.example.testproject.exception.CustomException;
import lombok.Data;

/**
 * @Author: 张昕
 * @Date： 2021/8/25
 * @Time: 17:42
 **/
@Data
public class ResultDTO {
    private Integer code;
    private String message;

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
}
