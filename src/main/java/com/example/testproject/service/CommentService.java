package com.example.testproject.service;

import com.example.testproject.enums.CommentTypeEnum;
import com.example.testproject.exception.CustomErrorCode;
import com.example.testproject.exception.CustomException;
import com.example.testproject.mapper.CommentDynamicSqlSupport;
import com.example.testproject.mapper.CommentMapper;
import com.example.testproject.mapper.QuestionDynamicSqlSupport;
import com.example.testproject.mapper.QuestionMapper;
import com.example.testproject.model.Comment;
import com.example.testproject.model.Question;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.Buildable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author: 张昕
 * @Date： 2021/8/25
 * @Time: 14:33
 **/
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    QuestionMapper questionMapper;

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null) {
            throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomException(CustomErrorCode.TYPE_NOT_FOUND);
        }
        if ("".equals(comment.getContent()) || comment.getContent() == null) {
            throw new CustomException(CustomErrorCode.CONTENT_IS_EMPTY);
        }

        if (comment.getType().equals(CommentTypeEnum.TYPE_FIRST.getType())) {
            //问题的评论
            Optional<Question> questionExist = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (!questionExist.isPresent()) {
                throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
            }
        } else if (comment.getType().equals(CommentTypeEnum.TYPE_SECOND.getType())) {
            //评论的评论
            Optional<Comment> commentExist = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (!commentExist.isPresent()) {
                throw new CustomException(CustomErrorCode.COMMENT_NOT_FOUND);
            }
        }
        //更新评论
        commentMapper.insertSelective(comment);
        //更新问题的评论数量
        UpdateStatementProvider updateStatementProvider;
        if (comment.getType().equals(CommentTypeEnum.TYPE_SECOND.getType())) {
            Buildable<SelectModel> sql = select(CommentDynamicSqlSupport.parentId).from(CommentDynamicSqlSupport.comment)
                    .where(CommentDynamicSqlSupport.id, isEqualTo(comment.getParentId()))
                    .and(CommentDynamicSqlSupport.type, isEqualTo(CommentTypeEnum.TYPE_FIRST.getType()));
            //评论的评论需要通过二级评论的id找到问题的id
            updateStatementProvider = update(QuestionDynamicSqlSupport.question)
                    .set(QuestionDynamicSqlSupport.commentCount)
                    .equalTo(add(QuestionDynamicSqlSupport.commentCount, constant("0"), constant("1")))
                    .where(QuestionDynamicSqlSupport.id, isEqualTo(sql))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
        } else {
            //问题的评论，那么问题的评论数量直接加1
            updateStatementProvider = update(QuestionDynamicSqlSupport.question)
                    .set(QuestionDynamicSqlSupport.commentCount)
                    .equalTo(add(QuestionDynamicSqlSupport.commentCount, constant("0"), constant("1")))
                    .where(QuestionDynamicSqlSupport.id, isEqualTo(comment.getParentId()))
                    .build().render(RenderingStrategies.MYBATIS3);
        }
        questionMapper.update(updateStatementProvider);
    }
}
