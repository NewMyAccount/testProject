package com.example.testproject.schedule;

import com.example.testproject.cache.HotTagCache;
import com.example.testproject.dto.HotTagDTO;
import com.example.testproject.mapper.QuestionDynamicSqlSupport;
import com.example.testproject.mapper.QuestionMapper;
import com.example.testproject.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.select;

/**
 * @Author: 张昕
 * @Date： 2021/11/12
 * @Time: 9:11
 **/
@Component
@Slf4j
public class HotTagSchedule {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 10)
//    @Scheduled(cron=". . .")
    public void reportCurrentTime() {
//        log.info("The time is now {}", new Date());
        SelectStatementProvider selectStatementProvider = select(QuestionDynamicSqlSupport.question.allColumns())
                .from(QuestionDynamicSqlSupport.question)
                .build().render(RenderingStrategies.MYBATIS3);
        List<Question> questionList = questionMapper.selectMany(selectStatementProvider);

        //k是标签，v是标签的权重值
        Map<String, Integer> tagMap = new HashMap<>();
        for (Question question : questionList) {
            tagMap.put(question.getTag(), tagMap.getOrDefault(question.getTag(), 0) + question.getViewCount() + question.getCommentCount() * 2);
        }
        //map转换成list，然后排序拿出前五个。
        Queue<HotTagDTO> queue = new PriorityQueue<>();
        List<HotTagDTO> tagDTOList = new ArrayList<>();
        tagMap.forEach((k, v) -> {
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(k);
            hotTagDTO.setPriority(v);
            queue.add(hotTagDTO);
        });
        while(tagDTOList.size() < 5){
            tagDTOList.add(0, queue.poll());
        }
        hotTagCache.setTags(tagDTOList);
    }
}
