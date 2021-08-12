package com.example.testproject.controller;

import com.example.testproject.dto.PaginationDTO;
import com.example.testproject.mapper.UserMapper;
import com.example.testproject.model.User;
import com.example.testproject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 张昕
 * @Date： 2021/8/10
 * @Time: 13:54
 **/
@Controller
public class ProfileController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          Model model) {
        if ("questions".equals(action)) {
            User user = (User) request.getSession().getAttribute("user");
            PaginationDTO paginationDTO = questionService.List(user, page, size);
            model.addAttribute("pagination", paginationDTO);
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            return "profile";
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "消息列表");
            return "profile";
        }
        return null;
    }
}
