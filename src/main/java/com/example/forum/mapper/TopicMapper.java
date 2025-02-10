package com.example.forum.mapper;

import com.example.forum.dto.post.PostInListDTO;
import com.example.forum.dto.topic.TopicDetailsDTO;
import com.example.forum.dto.topic.TopicInListDTO;
import com.example.forum.entity.Category;
import com.example.forum.entity.Post;
import com.example.forum.entity.Topic;
import com.example.forum.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class TopicMapper {



    private TopicMapper() {

    }




    public static TopicInListDTO toInListDTO(Topic topic) {
        if (topic == null) {
            return null;
        }
        TopicInListDTO topicDTO = new TopicInListDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setTitle(topic.getTitle());
        return topicDTO;
    }

    public static TopicDetailsDTO toDetailsDTO(Topic topic) {
        if (topic == null) {
            return null;
        }
        TopicDetailsDTO topicDTO = new TopicDetailsDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setTitle(topic.getTitle());

        Category category = topic.getCategory();
        topicDTO.setCategory(Optional.ofNullable(category).map(CategoryMapper::toDTO).orElse(null));

        List<Topic> children = Optional.ofNullable(topic.getChildren()).orElse(Collections.emptyList());
        List<TopicInListDTO> childrenDTO = children.stream().map(TopicMapper::toInListDTO).collect(Collectors.toList());
        topicDTO.setChildren(childrenDTO);

        List<Post> posts = Optional.ofNullable(topic.getPosts()).orElse(Collections.emptyList());
        List<PostInListDTO> postsDTO = posts.stream().map(PostMapper::toDTO).collect(Collectors.toList());
        topicDTO.setPosts(postsDTO);


        return topicDTO;
    }
}