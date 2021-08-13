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

    public PaginationDTO pageHelper(List<QuestionDTO> questionDTOList, Integer page, Integer pageNumber) {
        PaginationDTO paginationDTO = new PaginationDTO();

        List<Integer> pageList = new ArrayList<>();
        pageList.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pageList.add(0, page - i);
            }
            if (page + i <= pageNumber) {
                pageList.add(page + i);
            }
        }
        //是否展示上一页
        paginationDTO.setShowPrevious(page != 1);
        //是否展示下一页
        paginationDTO.setShowNext(!page.equals(pageNumber));
        //是否展示首页
        paginationDTO.setShowFirstPage(!pageList.contains(1));
        //是否展示末页
        paginationDTO.setShowLastPage(!pageList.contains(pageNumber));
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setTotalNumber(pageNumber);
        paginationDTO.setPages(pageList);
        paginationDTO.setCurrentPage(page);
        return paginationDTO;
    }
}
