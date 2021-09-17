package com.example.testproject.mapper;

import com.example.testproject.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: 张昕
 * @Date： 2021/9/17
 * @Time: 9:45
 **/

@Mapper
public interface QuestionExtMapper {
    @Select({"select * from question where tag regexp #{tag}"})
    List<Question> selectWithWhereClause(String tag);
}
