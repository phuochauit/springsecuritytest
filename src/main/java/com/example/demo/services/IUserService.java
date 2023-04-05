package com.example.demo.services;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    String authenticate(LoginDto loginDto);
    ResponseEntity<?> register (RegisterDto registerDto);
    Role saveRole(Role role);
    User saveUser(User user);
}
