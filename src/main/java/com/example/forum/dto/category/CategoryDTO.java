package com.example.forum.dto.category;

import com.example.forum.dto.post.PostInListDTO;
import com.example.forum.dto.topic.TopicInListDTO;
import lombok.Data;

import java.util.List;


@Data
public class CategoryDTO {
    private long id;
    private String name;
    private String description;
    private List<TopicInListDTO> topics;
}
