package com.example.testproject.service;

import com.example.testproject.dto.PaginationDTO;
import com.example.testproject.dto.QuestionDTO;
import com.example.testproject.mapper.QuestionMapper;
import com.example.testproject.mapper.UserMapper;
import com.example.testproject.model.Question;
import com.example.testproject.model.User;
import com.example.testproject.provider.PageProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

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
        //查找总页数
        Integer totolNumber = questionMapper.findTotolNumber();
        if (page < 1) {
            page = 1;
        } else if (page > totolNumber) {
            page = totolNumber;
        }
        //偏移量，从第几个数据开始找
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.select(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            User user = userMapper.findById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        PaginationDTO paginationDTO = pageProvider.pageHelper(questionDTOList, page, size, totolNumber);
        return paginationDTO;
    }

    public PaginationDTO List(User user, Integer page, Integer size) {
        //查找总页数
        Integer totolNumber = questionMapper.findTotolNumber();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if (page < 1) {
            page = 1;
        } else if (page > totolNumber) {
            page = totolNumber;
        }
        //偏移量，从第几个数据开始找
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.selectById(user.getAccountId(), offset, size);
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTOList.add(questionDTO);
            questionDTO.setUser(user);
        }
        PaginationDTO paginationDTO = pageProvider.pageHelper(questionDTOList, page, size, totolNumber);
        return paginationDTO;
    }
}
