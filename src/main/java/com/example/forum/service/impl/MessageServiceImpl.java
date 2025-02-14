package com.example.forum.service.impl;

import com.example.forum.dto.message.MessageDetailsDTO;
import com.example.forum.entity.Message;
import com.example.forum.mapper.MessageMapper;
import com.example.forum.repository.MessageRepository;
import com.example.forum.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public List<MessageDetailsDTO> findByPostId(long id) {

        List<Message> messages = messageRepository.findMessagesByPostId(id);
        return messages.stream().map(messageMapper::toDetailsDTO).toList();
    }


}
