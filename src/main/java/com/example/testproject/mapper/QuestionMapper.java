package com.example.testproject.mapper;

import com.example.testproject.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: 张昕
 * @Date： 2021/7/28
 * @Time: 16:18
 **/
@Mapper
public interface QuestionMapper {
    @Insert("Insert into question(title, description, gmt_create, gmt_modified, creator, tag) " +
            "values(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    @Select("Select * from question")
    List<Question> select();
}
