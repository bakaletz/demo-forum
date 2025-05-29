package com.example.forum.mapper;

import com.example.forum.dto.message.LastMessageDTO;
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
        messageDetailsDTO.setPost(postMapper.toDetailsDTO(post));
        User user = message.getCreatedBy();
        messageDetailsDTO.setCreatedBy(UserMapper.toDTO(user));

        return messageDetailsDTO;
    }
    public LastMessageDTO toLastMessageDTO(Message message){
        LastMessageDTO lastMessageDTO = new LastMessageDTO();
        lastMessageDTO.setId(message.getId());
        lastMessageDTO.setCreatedAt(message.getCreatedAt());
        User user = message.getCreatedBy();
        lastMessageDTO.setCreatedBy(UserMapper.toDTO(user));
        Post post = message.getPost();
        lastMessageDTO.setPost(postMapper.toDTOForLastMessage(post));
        return lastMessageDTO;
    }
}
