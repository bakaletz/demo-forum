package com.example.forum.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegisterDTO {
    @NotBlank(message = "Username is required")
    @Length(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @NotBlank(message = "Password is required")
    @Length(min = 4, max = 20, message = "Password must be between 4 and 20 characters")
    private String password;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

}
