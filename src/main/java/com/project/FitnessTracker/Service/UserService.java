package com.project.FitnessTracker.Service;

import com.project.FitnessTracker.DTO.LoginRequest;
import com.project.FitnessTracker.DTO.RegisterRequest;
import com.project.FitnessTracker.DTO.UserResponse;
import com.project.FitnessTracker.Repositories.UserRepository;
import com.project.FitnessTracker.model.User;
import com.project.FitnessTracker.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserResponse register(RegisterRequest request)
    {
        UserRole role=request.getRole()!=null? request.getRole():
                UserRole.USER;
        //Here id is set null as it will be considered new addition else hibernate will try to look for given id then update
        User user=User.builder().email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .userRole(role)
                .build();
        User temp=userRepository.save(user);
        UserResponse response=new UserResponse(temp.getId(), temp.getEmail(), temp.getFirstName(), temp.getLastName(), temp.getCreatedAt(),temp.getUpdatedAt());
        return response;
    }
    public User authenticate(LoginRequest request)
    {
        User user=userRepository.findByEmail(request.getEmail());
        if(user==null) throw new RuntimeException("Invalid Credentials");
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid Credentials");
        return user;
    }


}
