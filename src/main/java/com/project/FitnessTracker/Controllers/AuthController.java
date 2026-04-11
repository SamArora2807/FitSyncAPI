package com.project.FitnessTracker.Controllers;

import com.project.FitnessTracker.DTO.LoginRequest;
import com.project.FitnessTracker.DTO.LoginResponse;
import com.project.FitnessTracker.DTO.RegisterRequest;
import com.project.FitnessTracker.DTO.UserResponse;
import com.project.FitnessTracker.Repositories.UserRepository;
import com.project.FitnessTracker.Security.JwtUtils;
import com.project.FitnessTracker.Service.UserService;
import com.project.FitnessTracker.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    public final UserRepository userRepository;
    private final  PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try{
            User user=userService.authenticate(request);
            String token= jwtUtils.generateToken(user.getId(),user.getUserRole().name());
            return ResponseEntity.ok(new LoginResponse(token,new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getCreatedAt(),user.getUpdatedAt())));
        }
        catch (AuthenticationException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }

}
