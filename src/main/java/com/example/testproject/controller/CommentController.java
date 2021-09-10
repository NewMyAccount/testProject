package com.example.testproject.controller;

import com.example.testproject.dto.CommentCreateDTO;
import com.example.testproject.dto.CommentDTO;
import com.example.testproject.dto.QuestionDTO;
import com.example.testproject.dto.ResultDTO;
import com.example.testproject.enums.CommentTypeEnum;
import com.example.testproject.exception.CustomErrorCode;
import com.example.testproject.exception.CustomException;
import com.example.testproject.model.Comment;
import com.example.testproject.model.User;
import com.example.testproject.service.CommentService;
import com.example.testproject.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: 张昕
 * @Date： 2021/8/24
 * @Time: 16:00
 **/
@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    QuestionService questionService;

    @ResponseBody
    @PostMapping("/comment")
    public ResultDTO doComment(@RequestBody CommentCreateDTO commentCreateDTO,
                               HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException(CustomErrorCode.USER_NOT_FOUND);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentCreateDTO, comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommenter(user.getId());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @GetMapping("/comment/{commentId}")
    public ResultDTO<List<CommentDTO>> subComment(@PathVariable(name = "commentId") Integer commentId) {
        List<CommentDTO> commentList = commentService.findById(commentId, CommentTypeEnum.TYPE_SECOND);
        return ResultDTO.okOf(commentList);
    }

}
