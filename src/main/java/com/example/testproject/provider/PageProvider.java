package com.example.testproject.provider;

import com.example.testproject.dto.PaginationDTO;
import com.example.testproject.dto.QuestionDTO;
import com.example.testproject.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张昕
 * @Date： 2021/8/5
 * @Time: 11:07
 **/

@Component
public class PageProvider {
    @Autowired
    QuestionMapper questionMapper;

    public PaginationDTO pageHelper(List<QuestionDTO> questionDTOList, Integer page, Integer size, Integer totolNumber) {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestions(questionDTOList);
        List<Integer> pageList = new ArrayList<>();
        //总页数
        Integer totalPageNumber;
        if (totolNumber % size == 0) {
            totalPageNumber = totolNumber / size;
        } else {
            totalPageNumber = totolNumber / size + 1;
        }
        pageList.add(page);
        for(int i = 1; i <= 3; i++){
            if(page - i > 0){
                pageList.add(0, page - i);
            }
            if(page + i <= totalPageNumber){
                pageList.add(page + i);
            }
        }
        //是否展示上一页
        if (page == 1) {
            paginationDTO.setShowPrevious(false);
        } else {
            paginationDTO.setShowPrevious(true);
        }
        //是否展示下一页
        if (page == totalPageNumber) {
            paginationDTO.setShowNext(false);
        } else {
            paginationDTO.setShowNext(true);
        }
        //是否展示首页
        if (!pageList.contains(1)) {
            paginationDTO.setShowFirstPage(true);
        } else {
            paginationDTO.setShowFirstPage(false);
        }
        //是否展示末页
        if (!pageList.contains(totalPageNumber)) {
            paginationDTO.setShowLastPage(true);
        } else {
            paginationDTO.setShowLastPage(false);
        }
        paginationDTO.setTotolNumber(totalPageNumber);
        paginationDTO.setPages(pageList);
        paginationDTO.setCurrentPage(page);
        return paginationDTO;
    }
}
