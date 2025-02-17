package com.example.forum.mapper;

import com.example.forum.dto.post.PostDTO;
import com.example.forum.dto.post.PostDetailsDTO;
import com.example.forum.dto.post.PostInListDTO;
import com.example.forum.entity.Post;
import com.example.forum.entity.Topic;
import com.example.forum.entity.User;
import com.example.forum.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final TopicMapper topicMapper;
    private final MessageRepository messageRepository;

    public  PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setPinned(post.isPinned());
        postDTO.setLocked(post.isLocked());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUpdatedAt(post.getUpdatedAt());
        Topic topic = post.getTopic();
        postDTO.setTopic(topic != null ? topicMapper.toInListDTO(topic) : null);
        User user = post.getCreatedBy();
        postDTO.setCreatedBy(topic != null ? UserMapper.toDTO(user) : null);
        return postDTO;
    }

    public PostInListDTO toInListDTO(Post post) {
        PostInListDTO postDTO = new PostInListDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setPinned(post.isPinned());
        postDTO.setLocked(post.isLocked());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUpdatedAt(post.getUpdatedAt());
        postDTO.setReplies(messageRepository.countMessageByPostId(post.getId())-1);

        Topic topic = post.getTopic();
        postDTO.setTopic(topic != null ? topicMapper.toInListDTO(topic) : null);

        User user = post.getCreatedBy();
        postDTO.setCreatedBy(topic != null ? UserMapper.toDTO(user) : null);
        return postDTO;
    }

    public PostDetailsDTO toDetailsDTO (Post post) {
        PostDetailsDTO postDTO = new PostDetailsDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setPinned(post.isPinned());
        postDTO.setLocked(post.isLocked());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUpdatedAt(post.getUpdatedAt());

        Topic topic = post.getTopic();
        postDTO.setTopic(topic != null ? topicMapper.toInListDTO(topic) : null);

        User user = post.getCreatedBy();
        postDTO.setCreatedBy(topic != null ? UserMapper.toDTO(user) : null);
        return postDTO;
    }


}
