package com.example.testproject.service;

import com.example.testproject.dto.CommentDTO;
import com.example.testproject.enums.CommentTypeEnum;
import com.example.testproject.enums.NotifyStatusEnum;
import com.example.testproject.enums.NotifyTypeEnum;
import com.example.testproject.exception.CustomErrorCode;
import com.example.testproject.exception.CustomException;
import com.example.testproject.mapper.*;
import com.example.testproject.model.Comment;
import com.example.testproject.model.Notification;
import com.example.testproject.model.Question;
import com.example.testproject.model.User;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.Buildable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    UserMapper userMapper;
    @Autowired
    NotificationMapper notificationMapper;

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
        if (comment.getType().equals(CommentTypeEnum.COMMENT_TYPE.getType())) {
            //问题的评论
            Optional<Question> questionExist = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (!questionExist.isPresent()) {
                throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
            }
        } else if (comment.getType().equals(CommentTypeEnum.REPLY_TYPE.getType())) {
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
        if (comment.getType().equals(CommentTypeEnum.REPLY_TYPE.getType())) {
            Buildable<SelectModel> sql = select(CommentDynamicSqlSupport.parentId).from(CommentDynamicSqlSupport.comment)
                    .where(CommentDynamicSqlSupport.id, isEqualTo(comment.getParentId()))
                    .and(CommentDynamicSqlSupport.type, isEqualTo(CommentTypeEnum.COMMENT_TYPE.getType()));
            //评论的评论需要通过二级评论的id找到问题的id
            updateStatementProvider = update(QuestionDynamicSqlSupport.question)
                    .set(QuestionDynamicSqlSupport.commentCount)
                    .equalTo(add(QuestionDynamicSqlSupport.commentCount, constant("0"), constant("1")))
                    .where(QuestionDynamicSqlSupport.id, isEqualTo(sql))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
            //还需要增加该评论的评论数量
            UpdateStatementProvider commentCount = update(CommentDynamicSqlSupport.comment)
                    .set(CommentDynamicSqlSupport.commentCount)
                    .equalTo(add(CommentDynamicSqlSupport.commentCount, constant("0"), constant("1")))
                    .where(CommentDynamicSqlSupport.id, isEqualTo(comment.getParentId()))
                    .and(CommentDynamicSqlSupport.type, isEqualTo(CommentTypeEnum.COMMENT_TYPE.getType()))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
            commentMapper.update(commentCount);
            //通知被评论人
            notice(comment.getCommenter(), comment.getParentId(), NotifyTypeEnum.REPLY_TYPE);
        } else {
            //问题的评论，那么问题的评论数量直接加1
            updateStatementProvider = update(QuestionDynamicSqlSupport.question)
                    .set(QuestionDynamicSqlSupport.commentCount)
                    .equalTo(add(QuestionDynamicSqlSupport.commentCount, constant("0"), constant("1")))
                    .where(QuestionDynamicSqlSupport.id, isEqualTo(comment.getParentId()))
                    .build().render(RenderingStrategies.MYBATIS3);
            //通知被评论人
            notice(comment.getCommenter(), comment.getParentId(), NotifyTypeEnum.COMMENT_TYPE);
        }
        questionMapper.update(updateStatementProvider);
    }

    private void notice(Integer notifier, Integer id, NotifyTypeEnum notifyTypeEnum) {
        Notification notification = new Notification();
        if (notifyTypeEnum.getType().equals(NotifyTypeEnum.COMMENT_TYPE.getType())) {
            //通过问题的id获取问题发起人的id
            SelectStatementProvider selectStatementProvider = select(QuestionDynamicSqlSupport.creator)
                    .from(QuestionDynamicSqlSupport.question)
                    .where(QuestionDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
            Optional<Question> question = questionMapper.selectOne(selectStatementProvider);
            notification.setReceiver(question.get().getCreator());
        } else {
            //通过回复的id获取被回复人的id
            Optional<Comment> comment = commentMapper.selectByPrimaryKey(id);
            notification.setReceiver(comment.get().getCommenter());
        }
        notification.setNotifier(notifier);
        notification.setType(notifyTypeEnum.getType());
        notification.setStatus(NotifyStatusEnum.UNREAD.getStatus());//未读
        notification.setOuterId(id);
        notification.setGmtCreate(System.currentTimeMillis());
        notificationMapper.insertSelective(notification);
    }


    //获取一级评论信息
    public List<CommentDTO> findById(Integer id, CommentTypeEnum typeEnum) {
        //获取一级评论
        SelectStatementProvider selectStatementProvider = select(CommentDynamicSqlSupport.comment.allColumns())
                .from(CommentDynamicSqlSupport.comment)
                .where(CommentDynamicSqlSupport.comment.type, isEqualTo(typeEnum.getType()))
                .and(CommentDynamicSqlSupport.comment.parentId, isEqualTo(id))
                .orderBy(CommentDynamicSqlSupport.gmtCreate.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<Comment> comments = commentMapper.selectMany(selectStatementProvider);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<Integer> commentersId = comments.stream().map(Comment::getCommenter).collect(Collectors.toSet());
        //获取评论人信息
        SelectStatementProvider selectByUserMapper = select(UserDynamicSqlSupport.user.allColumns())
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.user.id, isIn(commentersId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<User> commentUsers = userMapper.selectMany(selectByUserMapper);
        Map<Integer, User> userInfo = commentUsers.stream().collect(Collectors.toMap(User::getId, user -> user));

        //转换为DTO返回
        return comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userInfo.get(commentDTO.getCommenter()));
            return commentDTO;
        }).collect(Collectors.toList());
    }
}
