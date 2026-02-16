package com.example.todo.service;

import com.example.todo.dto.LoginRequestDto;
import com.example.todo.dto.RegisterRequestDto;
import com.example.todo.dto.TokenResponseDto;
import com.example.todo.model.UserModel;
import com.example.todo.repository.UserRepository;
import com.example.todo.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;
    private final JwtUtil jwtUtil;
    public AuthService(UserRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }
    public TokenResponseDto login(LoginRequestDto dto) {
        UserModel user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new TokenResponseDto(token);
    }
    public TokenResponseDto register(RegisterRequestDto dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        UserModel newUser = new UserModel();
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword());
        repository.save(newUser);
        String token = jwtUtil.generateToken(newUser.getEmail());
        return new TokenResponseDto(token);
    }
}
