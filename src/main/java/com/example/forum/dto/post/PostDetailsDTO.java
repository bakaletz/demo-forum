package com.example.forum.dto.post;

import com.example.forum.dto.user.UserDTO;
import com.example.forum.dto.topic.TopicInListDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDetailsDTO {
    private long id;
    private String title;
    private boolean isLocked;
    private boolean isPinned;
    private TopicInListDTO topic;
    private UserDTO createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
