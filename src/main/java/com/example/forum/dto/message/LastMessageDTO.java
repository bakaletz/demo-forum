package com.example.forum.dto.message;

import com.example.forum.dto.post.PostDTOForLastMessage;
import com.example.forum.dto.user.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LastMessageDTO {
    private long id;
    private PostDTOForLastMessage post;
    private LocalDateTime createdAt;
    private UserDTO createdBy;
    private LocalDateTime updatedAt;
}
