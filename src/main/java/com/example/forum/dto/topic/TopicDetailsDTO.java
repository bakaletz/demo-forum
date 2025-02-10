package com.example.forum.dto.topic;

import com.example.forum.dto.category.CategoryDTO;
import com.example.forum.dto.post.PostInListDTO;
import com.example.forum.entity.Topic;
import lombok.Data;

import java.util.List;

@Data
public class TopicDetailsDTO {
    private long id;
    private String title;
    private CategoryDTO category;
    private Topic parent;
    private List<TopicInListDTO> children;
    private List<PostInListDTO> posts;
    private long postCount;
}
