package com.example.forum.mapper;

import com.example.forum.dto.message.LastMessageDTO;
import com.example.forum.dto.message.MessageDetailsDTO;
import com.example.forum.dto.post.PostInListDTO;
import com.example.forum.dto.topic.TopicDetailsDTO;
import com.example.forum.dto.topic.TopicInListDTO;
import com.example.forum.entity.Category;
import com.example.forum.entity.Message;
import com.example.forum.entity.Post;
import com.example.forum.entity.Topic;
import com.example.forum.repository.MessageRepository;
import com.example.forum.repository.PostRepository;
import com.example.forum.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TopicMapper {

    private final CategoryMapper categoryMapper;
    private final TopicRepository topicRepository;

    private PostMapper postMapper;
    private MessageMapper messageMapper;

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Autowired
    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public TopicInListDTO toInListDTO(Topic topic) {
        if (topic == null) {
            return null;
        }
        TopicInListDTO topicDTO = new TopicInListDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setTitle(topic.getTitle());
        topicDTO.setPostsCount(topicRepository.countPostsInTopicAndChildren(topic.getId()));
        topicDTO.setMessagesCount(topicRepository.countMessagesInTopicAndChildren(topic.getId()));

        Message lastMessage = topicRepository.findLatestMessageInTopicAndChildren(topic.getId());
        topicDTO.setLastMessage(lastMessage != null ? messageMapper.toLastMessageDTO(lastMessage): null);
        return topicDTO;
    }

    public TopicDetailsDTO toDetailsDTO(Topic topic) {
        if (topic == null) {
            return null;
        }
        TopicDetailsDTO topicDTO = new TopicDetailsDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setTitle(topic.getTitle());

        Category category = topic.getCategory();
        topicDTO.setCategory(Optional.ofNullable(category).map(categoryMapper::toDTO).orElse(null));

        List<Topic> children = Optional.ofNullable(topic.getChildren()).orElse(Collections.emptyList());
        List<TopicInListDTO> childrenDTO = children.stream().map(this::toInListDTO).collect(Collectors.toList());
        topicDTO.setChildren(childrenDTO);

        List<Post> posts = Optional.ofNullable(topic.getPosts()).orElse(Collections.emptyList());
        List<PostInListDTO> postsDTO = posts.stream().map(postMapper::toInListDTO).collect(Collectors.toList());
        topicDTO.setPosts(postsDTO);

        return topicDTO;
    }
}