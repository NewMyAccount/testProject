package com.example.testproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 张昕
 * @Date： 2021/7/28
 * @Time: 9:54
 **/
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
