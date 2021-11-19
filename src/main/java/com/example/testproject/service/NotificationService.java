package com.example.testproject.service;

import com.example.testproject.dto.NotificationDTO;
import com.example.testproject.dto.PaginationDTO;
import com.example.testproject.enums.NotifyStatusEnum;
import com.example.testproject.enums.NotifyTypeEnum;
import com.example.testproject.exception.CustomErrorCode;
import com.example.testproject.exception.CustomException;
import com.example.testproject.mapper.*;
import com.example.testproject.model.Comment;
import com.example.testproject.model.Notification;
import com.example.testproject.model.Question;
import com.example.testproject.model.User;
import com.example.testproject.provider.PageProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.testproject.mapper.NotificationDynamicSqlSupport.notification;
import static com.example.testproject.mapper.NotificationDynamicSqlSupport.receiver;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author: 张昕
 * @Date： 2021/11/3
 * @Time: 15:37
 **/
@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PageProvider pageProvider;

    public PaginationDTO list(User user, Integer page, Integer size) {
        //查找问题总个数
        SelectStatementProvider findTotalNumberById = select(count())
                .from(notification)
                .where(notification.receiver, isEqualTo(user.getId()))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        long totalNumber = notificationMapper.count(findTotalNumberById);
        Map<String, Integer> map = pageProvider.countPageNumber(page, size, (int) totalNumber);
        //偏移量，从第几个数据开始找
        int offset = size * (map.get("currentPage") - 1);
        SelectStatementProvider selectById = select(notification.allColumns())
                .from(notification)
                .where(notification.receiver, isEqualTo(user.getId()))
                .orderBy(notification.gmtCreate.descending())
                .limit(size)
                .offset(offset)
                .build().render(RenderingStrategies.MYBATIS3);
        //1.先找到所有的通知
        List<Notification> notificationList = notificationMapper.selectMany(selectById);
        //2.通过通知找到notifier
        List<Integer> notifierIds = notificationList.stream().map(notificationInfo -> notificationInfo.getNotifier()).distinct().collect(Collectors.toList());
        SelectStatementProvider selectStatementProvider = select(UserDynamicSqlSupport.user.allColumns())
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.id, isIn(notifierIds))
                .build().render(RenderingStrategies.MYBATIS3);
        List<User> users = userMapper.selectMany(selectStatementProvider);
        Map<Integer, User> usersInfo = users.stream().collect(Collectors.toMap(User::getId, userInfo -> userInfo));
        //3.然后找问题的title和评论的内容
        Set<Integer> titleIds = notificationList.stream()
                .filter(notificationInfo -> notificationInfo.getType().equals(NotifyTypeEnum.COMMENT_TYPE.getType()))
                .map(notificationInfo -> notificationInfo.getOuterId()).collect(Collectors.toSet());
        List<Question> questionList = new ArrayList<>();
        if (titleIds.size() != 0) {
            SelectStatementProvider questionTitleSQL = select(QuestionDynamicSqlSupport.question.allColumns())
                    .from(QuestionDynamicSqlSupport.question)
                    .where(QuestionDynamicSqlSupport.id, isIn(titleIds))
                    .build().render(RenderingStrategies.MYBATIS3);
            questionList = questionMapper.selectMany(questionTitleSQL);
        }
        Map<Integer, Question> questionInfo = questionList.stream().collect(Collectors.toMap(Question::getId, question -> question));
        //这个是找回复
        titleIds = notificationList.stream()
                .filter(notificationInfo -> notificationInfo.getType().equals(NotifyTypeEnum.REPLY_TYPE.getType()))
                .map(notificationInfo -> notificationInfo.getOuterId()).collect(Collectors.toSet());

        List<Comment> commentList = new ArrayList<>();
        if (titleIds.size() != 0) {
            SelectStatementProvider commentTitleSQL = select(CommentDynamicSqlSupport.comment.allColumns())
                    .from(CommentDynamicSqlSupport.comment)
                    .where(CommentDynamicSqlSupport.id, isIn(titleIds))
                    .build()
                    .render(RenderingStrategies.MYBATIS3);
            commentList = commentMapper.selectMany(commentTitleSQL);
        }
        Map<Integer, Comment> commentInfo = commentList.stream().collect(Collectors.toMap(Comment::getId, comment -> comment));
        //4.做转换
        List<NotificationDTO> notificationDTOList = notificationList.stream().map(notificationInfo -> {
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setId(notificationInfo.getId());
            notificationDTO.setNotifier(usersInfo.get(notificationInfo.getNotifier()));
            notificationDTO.setGmtCreate(notificationInfo.getGmtCreate());
            notificationDTO.setStatus(notificationInfo.getStatus());
            if (notificationInfo.getType().equals(NotifyTypeEnum.COMMENT_TYPE.getType())) {
                notificationDTO.setType(NotifyTypeEnum.COMMENT_TYPE.getInformation());
                notificationDTO.setTitle(questionInfo.get(notificationInfo.getOuterId()).getTitle());
            } else {
                notificationDTO.setType(NotifyTypeEnum.REPLY_TYPE.getInformation());
                notificationDTO.setTitle(commentInfo.get(notificationInfo.getOuterId()).getContent());
            }
            return notificationDTO;
        }).collect(Collectors.toList());

        return pageProvider.pageHelper(notificationDTOList, map.get("currentPage"), map.get("totalPageNumber"));
    }

    public Long unreadCount(User user) {
        SelectStatementProvider selectStatementProvider = select(count())
                .from(notification)
                .where(receiver, isEqualTo(user.getId()))
                .and(NotificationDynamicSqlSupport.status, isEqualTo(NotifyStatusEnum.UNREAD.getStatus()))
                .build().render(RenderingStrategies.MYBATIS3);
        return notificationMapper.count(selectStatementProvider);
    }

    public Integer findQuestionById(Integer id, User user) {
        Optional<Notification> notification = notificationMapper.selectByPrimaryKey(id);
        if(!notification.isPresent()){
            throw new CustomException(CustomErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(!notification.get().getReceiver().equals(user.getId())){
            throw new CustomException(CustomErrorCode.USER_VERIFY_FAIL);
        }

        if (notification.get().getType().equals(NotifyTypeEnum.COMMENT_TYPE.getType())) {
            return questionMapper.selectByPrimaryKey(notification.get().getOuterId()).get().getId();
        }
        SelectStatementProvider selectStatementProvider = select(CommentDynamicSqlSupport.comment.parentId)
                .from(CommentDynamicSqlSupport.comment)
                .where(CommentDynamicSqlSupport.id, isEqualTo(notification.get().getOuterId()))
                .build().render(RenderingStrategies.MYBATIS3);
        return commentMapper.selectOne(selectStatementProvider).get().getParentId();
    }

    public void read(Integer id) {
        UpdateStatementProvider updateStatementProvider = update(notification)
                .set(notification.status)
                .equalTo(NotifyStatusEnum.READ.getStatus())
                .where(notification.id, isEqualTo(id))
                .build().render(RenderingStrategies.MYBATIS3);
        notificationMapper.update(updateStatementProvider);
    }
}