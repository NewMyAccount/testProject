package com.example.testproject.controller;

import com.example.testproject.dto.CommentDTO;
import com.example.testproject.model.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 张昕
 * @Date： 2021/8/24
 * @Time: 16:00
 **/
@Controller
public class CommentController {
    @ResponseBody
    @PostMapping("/comment")
    public Object list(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        return null;
    }
}
