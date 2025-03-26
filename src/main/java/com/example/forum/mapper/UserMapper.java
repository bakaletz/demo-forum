package com.example.forum.mapper;

import com.example.forum.dto.post.PostInListDTO;
import com.example.forum.dto.user.UserDTO;
import com.example.forum.entity.Post;
import com.example.forum.entity.Role;
import com.example.forum.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


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
        Set<Role> roles = Optional.ofNullable(user.getRoles()).orElse(Collections.emptySet());
        userDTO.setRole(roles);
        return userDTO;
    }
}
