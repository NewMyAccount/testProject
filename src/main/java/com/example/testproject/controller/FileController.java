package com.example.testproject.controller;

import com.example.testproject.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 张昕
 * @Date： 2021/11/9
 * @Time: 11:46
 **/
@Controller
public class FileController {

    @RequestMapping("/upload")
    @ResponseBody
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setMessage("测试图片");
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/baidu.jpg");
        return fileDTO;
    }
}
