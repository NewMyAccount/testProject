package com.example.testproject.mapper;

import com.example.testproject.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: 张昕
 * @Date： 2021/7/28
 * @Time: 16:18
 **/
@Mapper
public interface QuestionMapper {
    @Insert("Insert into question(title, description, gmt_create, gmt_modified, creator, tag)" +
            "values(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    @Select("Select * from question limit #{offset}, #{size}")
    List<Question> select(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer findTotalNumber();

    @Select("select count(1) from question where creator = #{id}")
    Integer findTotalNumberById(Integer id);

    @Select("select * from question where creator = #{id} limit #{offset}, #{size}")
    List<Question> selectById(Integer id, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select * from question where id = #{id}")
    Question findById(@Param("id") Integer id);
}
