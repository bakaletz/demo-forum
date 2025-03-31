package com.example.forum.dto.user;

import com.example.forum.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private String avatar;
    private Role role;
}
