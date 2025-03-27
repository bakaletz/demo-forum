package com.example.forum.controller;


import com.example.forum.constants.ApplicationConstants;
import com.example.forum.dto.LoginRequestDTO;
import com.example.forum.dto.LoginResponseDTO;
import com.example.forum.dto.user.UserDTO;
import com.example.forum.dto.user.UserRegisterDTO;
import com.example.forum.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("user")
    public ResponseEntity<UserDTO> getUserDetailsAfterLogin(Authentication authentication) {
        UserDTO userDTO = userService.findUser(authentication);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userDTO);
    }

    @PostMapping("apiLogin")
    public ResponseEntity<LoginResponseDTO> apiLogin (@RequestBody LoginRequestDTO loginRequest) {
        String jwt = userService.authenticateAndGenerateToken(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwt)
                .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterDTO userDTO) {
        try {
            String responseMessage = userService.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred: " + ex.getMessage());
        }
    }
}
