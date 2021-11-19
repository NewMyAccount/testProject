package com.example.testproject.cache;

import com.example.testproject.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张昕
 * @Date： 2021/11/12
 * @Time: 14:26
 **/
@Component
@Data
public class HotTagCache {
    private List<HotTagDTO> tags = new ArrayList<>();
}
