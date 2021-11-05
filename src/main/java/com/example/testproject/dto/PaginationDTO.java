package com.example.testproject.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: 张昕
 * @Date： 2021/8/5
 * @Time: 10:58
 **/

@Data
public class PaginationDTO<T> {
    private List<T> data;
    private List<Integer> pages;
    private Integer currentPage;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;
    private Integer totalNumber;
}
