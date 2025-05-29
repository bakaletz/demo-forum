package com.example.forum.dto.topic;

import com.example.forum.dto.category.CategoryDTO;
import com.example.forum.dto.message.LastMessageDTO;
import com.example.forum.dto.message.MessageDetailsDTO;
import lombok.Data;


@Data
public class TopicInListDTO {
    private long id;
    private String title;
    private long postsCount;
    private long messagesCount;
    private LastMessageDTO lastMessage;
}
