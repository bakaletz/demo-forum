package com.example.forum.mapper;

import com.example.forum.dto.post.PostInListDTO;
import com.example.forum.entity.Post;
import com.example.forum.entity.Topic;
import com.example.forum.entity.User;

public class PostMapper {
    private PostMapper() {

    }

    public static PostInListDTO toDTO(Post post) {
        PostInListDTO postDTO = new PostInListDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setPinned(post.isPinned());
        postDTO.setLocked(post.isLocked());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUpdatedAt(post.getUpdatedAt());

        Topic topic = post.getTopic();
        postDTO.setTopic(topic != null ? TopicMapper.toDTO(topic) : null);

        User user = post.getCreatedBy();
        postDTO.setCreatedBy(topic != null ? UserMapper.toDTO(user) : null);
        return postDTO;
    }
}
