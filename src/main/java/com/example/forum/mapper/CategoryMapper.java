package com.example.forum.mapper;

import com.example.forum.dto.category.CategoryDTO;
import com.example.forum.dto.topic.TopicInListDTO;
import com.example.forum.entity.Category;
import com.example.forum.entity.Topic;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        List<Topic> topics = Optional.ofNullable(category.getTopics()).orElse(Collections.emptyList());
        List<TopicInListDTO> topicsDTO = topics.stream().map(TopicMapper::toInListDTO).collect(Collectors.toList());
        categoryDTO.setTopics(topicsDTO);
        return categoryDTO;
    }
}
