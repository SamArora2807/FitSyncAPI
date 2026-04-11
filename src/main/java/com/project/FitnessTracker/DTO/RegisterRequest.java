package com.project.FitnessTracker.DTO;


import com.project.FitnessTracker.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message="email is required")
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
}