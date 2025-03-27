package com.example.forum.service;

import com.example.forum.dto.LoginRequestDTO;
import com.example.forum.dto.user.UserDTO;
import com.example.forum.dto.user.UserRegisterDTO;
import com.example.forum.entity.User;
import org.springframework.security.core.Authentication;


public interface UserService {

    UserDTO findUser(Authentication authentication);

    String authenticateAndGenerateToken(LoginRequestDTO loginRequest);

    String registerUser(UserRegisterDTO userDTO);
}
