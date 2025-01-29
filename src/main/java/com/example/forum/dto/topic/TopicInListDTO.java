package com.example.forum.dto.topic;

import com.example.forum.dto.category.CategoryDTO;
import lombok.Data;


@Data
public class TopicInListDTO {
    private long id;
    private String title;
    private long postCount;
}
