package com.example.forum.service;

import com.example.forum.dto.message.MessageDetailsDTO;
import com.example.forum.entity.Message;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {

    List<MessageDetailsDTO> findByPostId(long id);
}
