package com.example.testproject.provider;

import com.example.testproject.dto.PaginationDTO;
import com.example.testproject.dto.QuestionDTO;
import com.example.testproject.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 张昕
 * @Date： 2021/8/5
 * @Time: 11:07
 **/

@Component
public class PageProvider {
    @Autowired
    QuestionMapper questionMapper;

    public <T> PaginationDTO pageHelper(List<T> list, Integer page, Integer pageNumber) {
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
        paginationDTO.setData(list);
        paginationDTO.setTotalNumber(pageNumber);
        paginationDTO.setPages(pageList);
        paginationDTO.setCurrentPage(page);
        return paginationDTO;
    }

    public Map<String, Integer> countPageNumber(Integer page, Integer size, Integer totalNumber) {
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
}
