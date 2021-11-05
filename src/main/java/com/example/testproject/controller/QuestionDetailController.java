package com.example.testproject.controller;

import com.example.testproject.dto.CommentDTO;
import com.example.testproject.dto.QuestionDTO;
import com.example.testproject.enums.CommentTypeEnum;
import com.example.testproject.service.CommentService;
import com.example.testproject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author: 张昕
 * @Date： 2021/8/16
 * @Time: 15:27
 **/
@Controller
public class QuestionDetailController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/questionDetail/{id}")
    private String questionDetails(@PathVariable(name = "id") Integer id, Model model) {
        QuestionDTO questionDTO = questionService.findById(id);
        List<CommentDTO> commentList = commentService.findById(id, CommentTypeEnum.COMMENT_TYPE);
        List<QuestionDTO> questionDTOList = questionService.findRelatedQuestion(questionDTO);
        model.addAttribute("questionDetail", questionDTO);
        model.addAttribute("comments", commentList);
        model.addAttribute("relatedQuestions", questionDTOList);
        return "questionDetail";
    }
}
