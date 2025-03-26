package com.example.forum.controller;


import com.example.forum.constants.ApplicationConstants;
import com.example.forum.dto.LoginRequestDTO;
import com.example.forum.dto.LoginResponseDTO;
import com.example.forum.dto.user.UserDTO;
import com.example.forum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("api/v1/user")
    public ResponseEntity<UserDTO> getUserDetailsAfterLogin(Authentication authentication) {
        UserDTO userDTO = userService.findUser(authentication);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userDTO);
    }

    @PostMapping("/api/v1/apiLogin")
    public ResponseEntity<LoginResponseDTO> apiLogin (@RequestBody LoginRequestDTO loginRequest) {
        String jwt = userService.authenticateAndGenerateToken(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwt)
                .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
    }
}
