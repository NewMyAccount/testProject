package com.example.testproject.controller;

import com.example.testproject.model.User;
import com.example.testproject.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 张昕
 * @Date： 2021/11/5
 * @Time: 11:10
 **/
@Controller

public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/notification/{id}")
    private String questionDetails(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Integer questionId = notificationService.findQuestionById(id, user);
        notificationService.read(id);
        return "redirect:/questionDetail/" + questionId;
    }
}
