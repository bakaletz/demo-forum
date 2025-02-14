package com.example.forum.mapper;

import com.example.forum.dto.message.MessageDetailsDTO;
import com.example.forum.entity.Message;
import com.example.forum.entity.Post;
import com.example.forum.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private PostMapper postMapper;

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public MessageDetailsDTO toDetailsDTO(Message message){
        MessageDetailsDTO messageDetailsDTO = new MessageDetailsDTO();
        messageDetailsDTO.setId(message.getId());
        messageDetailsDTO.setText(message.getText());
        messageDetailsDTO.setCreatedAt(message.getCreatedAt());
        Post post = message.getPost();
        messageDetailsDTO.setPost(postMapper.toDTO(post));
        User user = message.getCreatedBy();
        messageDetailsDTO.setCreatedBy(UserMapper.toDTO(user));

        return messageDetailsDTO;
    }
}
