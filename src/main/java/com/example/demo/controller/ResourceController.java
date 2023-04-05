package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dtos.LoginDto;
import com.example.demo.services.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResourceController extends GenericController {
    private final IUserService iUserService;
    @GetMapping("/")
    public String loginEndpoint() {
        return "<h1>Hello! Welcome to my website.</h1>";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "<h1>Hello! Welcome to user role page.</h1>" +
                "<a href='/admin'>admin</a>";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "<h1>Hello! Welcome to admin role page.</h1>" +
                "<a href='/user'>user</a>";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginDto loginDto)
    { return  iUserService.authenticate(loginDto);}
}
