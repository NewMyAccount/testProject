package com.example.testproject.service;

import com.example.testproject.dto.PaginationDTO;
import com.example.testproject.dto.QuestionDTO;
import com.example.testproject.exception.CustomErrorCode;
import com.example.testproject.exception.CustomException;
import com.example.testproject.mapper.QuestionDynamicSqlSupport;
import com.example.testproject.mapper.QuestionMapper;
import com.example.testproject.mapper.UserMapper;
import com.example.testproject.model.Question;
import com.example.testproject.model.User;
import com.example.testproject.provider.PageProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.testproject.mapper.QuestionDynamicSqlSupport.question;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author: 张昕
 * @Date： 2021/8/3
 * @Time: 9:44
 **/
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PageProvider pageProvider;

    public PaginationDTO list(Integer page, Integer size) {
        //查找问题总个数
        SelectStatementProvider selectStatementProvider = select(count())
                .from(question).build().render(RenderingStrategies.MYBATIS3);
        long totalNumber = questionMapper.count(selectStatementProvider);
        //页数少的情况下可以这么用
        Map<String, Integer> map = countPageNumber(page, size, (int) totalNumber);
        //偏移量，从第几个数据开始找
        int offset = size * (map.get("currentPage") - 1);
        SelectStatementProvider selectNum = select(question.allColumns())
                .from(question).limit(size).offset(offset)
                .build().render(RenderingStrategies.MYBATIS3);
        List<Question> questionList = questionMapper.selectMany(selectNum);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            Optional<User> user = userMapper.selectByPrimaryKey(question.getCreator());
            questionDTO.setUser(user.orElse(null));
            questionDTOList.add(questionDTO);
        }
        return pageProvider.pageHelper(questionDTOList, map.get("currentPage"), map.get("totalPageNumber"));
    }

    public PaginationDTO list(User user, Integer page, Integer size) {
        //查找问题总个数
        SelectStatementProvider findTotalNumberById = select(count())
                .from(question)
                .where(question.creator, isEqualTo(user.getId()))
                .build().render(RenderingStrategies.MYBATIS3);
        long totalNumber = questionMapper.count(findTotalNumberById);
        Map<String, Integer> map = countPageNumber(page, size, (int) totalNumber);
        //偏移量，从第几个数据开始找
        int offset = size * (map.get("currentPage") - 1);
        SelectStatementProvider selectById = select(question.allColumns())
                .from(question)
                .where(question.creator, isEqualTo(user.getId()))
                .limit(size)
                .offset(offset)
                .build().render(RenderingStrategies.MYBATIS3);
        List<Question> questionList = questionMapper.selectMany(selectById);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTOList.add(questionDTO);
            questionDTO.setUser(user);
        }
        return pageProvider.pageHelper(questionDTOList, map.get("currentPage"), map.get("totalPageNumber"));
    }

    private Map<String, Integer> countPageNumber(Integer page, Integer size, Integer totalNumber) {
        if (page < 1) {
            page = 1;
        } else if (totalNumber != 0 && page > totalNumber) {
            page = totalNumber;
        } else if (totalNumber == 0) {
            page = 1;
        }
        //总页数
        int totalPageNumber;
        if (totalNumber % size == 0) {
            totalPageNumber = totalNumber / size;
        } else {
            totalPageNumber = totalNumber / size + 1;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("currentPage", page);
        //个数为0的情况下页数计算结果也为0，页数为0的情况下返回1。
        map.put("totalPageNumber", totalPageNumber != 0 ? totalPageNumber : 1);
        return map;
    }

    public QuestionDTO findById(Integer id) {

        Optional<Question> question = questionMapper.selectByPrimaryKey(id);
        if (!question.isPresent()) {
            throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
        }
        //增加阅读数量
        UpdateStatementProvider updateStatementProvider = update(QuestionDynamicSqlSupport.question)
                .set(QuestionDynamicSqlSupport.viewCount)
                .equalTo(add(QuestionDynamicSqlSupport.viewCount, constant("0"), constant("1")))
                .where(QuestionDynamicSqlSupport.id, isEqualTo(id))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        questionMapper.update(updateStatementProvider);

        QuestionDTO questionDTO = new QuestionDTO();
        Optional<User> user = userMapper.selectByPrimaryKey(question.get().getCreator());
        BeanUtils.copyProperties(question.get(), questionDTO);
        questionDTO.setUser(user.orElse(null));
        return questionDTO;
    }


    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertSelective(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            int number = questionMapper.updateByPrimaryKeySelective(question);
            if (number != 1) {
                throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }
}
