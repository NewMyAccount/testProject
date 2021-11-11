package com.example.testproject.controller;

import com.example.testproject.dto.PaginationDTO;
import com.example.testproject.mapper.UserMapper;
import com.example.testproject.model.Notification;
import com.example.testproject.model.User;
import com.example.testproject.service.NotificationService;
import com.example.testproject.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProfileController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            PaginationDTO paginationDTO = questionService.list(user, page, size);
            model.addAttribute("pagination", paginationDTO);
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            model.addAttribute("unreadCount", notificationService.unreadCount(user));
            return "profile";
        } else if ("replies".equals(action)) {
            PaginationDTO paginationDTO = notificationService.list(user, page, size);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "我的消息");
            model.addAttribute("pagination", paginationDTO);
            return "profile";
        }
        return null;
    }
}
