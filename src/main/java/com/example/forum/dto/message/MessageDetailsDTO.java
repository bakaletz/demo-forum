package com.example.forum.dto.message;

import com.example.forum.dto.UserDTO;
import com.example.forum.dto.post.PostDTO;
import com.example.forum.dto.post.PostInListDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDetailsDTO {

    private long id;
    private PostDTO post;
    private String text;
    private LocalDateTime createdAt;
    private UserDTO createdBy;
    private LocalDateTime updatedAt;
}
