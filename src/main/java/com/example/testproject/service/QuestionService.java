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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Integer totalNumber = questionMapper.findTotalNumber();
        Map<String, Integer> map = countPageNumber(page, size, totalNumber);
        //偏移量，从第几个数据开始找
        Integer offset = size * (map.get("currentPage") - 1);
        List<Question> questionList = questionMapper.select(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            User user = userMapper.findById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return pageProvider.pageHelper(questionDTOList, map.get("currentPage"), map.get("totalPageNumber"));
    }

    public PaginationDTO list(User user, Integer page, Integer size) {
        //查找问题总个数
        Integer totalNumber = questionMapper.findTotalNumberById(user.getId());
        Map<String, Integer> map = countPageNumber(page, size, totalNumber);
        //偏移量，从第几个数据开始找
        Integer offset = size * (map.get("currentPage") - 1);
        List<Question> questionList = questionMapper.selectById(user.getId(), offset, size);
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
        Question question = questionMapper.findById(id);
        User user = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }
}
