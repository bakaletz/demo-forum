package com.example.forum.mapper;

import com.example.forum.dto.UserDTO;
import com.example.forum.entity.User;

public class UserMapper {

    private UserMapper() {

    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setLastLogin(user.getLastLogin());
        userDTO.setAvatar(user.getAvatar());
        return userDTO;
    }
}
