package com.example.testproject.controller;

import com.example.testproject.dto.PaginationDTO;
import com.example.testproject.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 张昕
 * @Date： 2021/7/15
 * @Time: 14:46
 **/

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search) {
        PaginationDTO paginationDTO;
        if (StringUtils.isBlank(search) || StringUtils.isEmpty(search)) {
            paginationDTO = questionService.list(page, size);
        } else {
            paginationDTO = questionService.list(search, page, size);
        }
        model.addAttribute("search", search);
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "";
    }
}
