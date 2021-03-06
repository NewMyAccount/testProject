package com.example.testproject.controller;

import com.example.testproject.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 张昕
 * @Date： 2021/11/9
 * @Time: 11:46
 **/
@Controller
@Slf4j
public class FileController {

    @RequestMapping("/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        FileDTO fileDTO = new FileDTO();
        fileDTO.setMessage("测试图片");
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/baidu.jpg");
        return fileDTO;
    }
}
