package com.example.forum.mapper;

import com.example.forum.dto.category.CategoryDTO;
import com.example.forum.dto.topic.TopicInListDTO;
import com.example.forum.entity.Category;
import com.example.forum.entity.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryMapper {


    private TopicMapper topicMapper;

    @Autowired
    public void setTopicMapper(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    public CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        List<Topic> topics = Optional.ofNullable(category.getTopics()).orElse(Collections.emptyList());
        List<TopicInListDTO> topicsDTO = topics.stream().map(topicMapper::toInListDTO).collect(Collectors.toList());
        categoryDTO.setTopics(topicsDTO);
        return categoryDTO;
    }
}
