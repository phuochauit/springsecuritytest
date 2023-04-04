package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController extends GenericController {
    @GetMapping("/")
    public String loginEndpoint() {
        return "<h1>Hello! Welcome to my website.</h1>";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "<h1>Hello! Welcome to admin role page.</h1>" +
                "<a href='/user'>user</a>";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "<h1>Hello! Welcome to user role page.</h1>" +
                "<a href='/admin'>admin</a>";
    }

    @GetMapping("/all")
    public String allRolesEndpoint() {
        return "<h1>Hello! Welcome to view All Roles.</h1>";
    }

    @DeleteMapping("/delete")
    public String deleteEndpoint(@RequestBody String s) {
        return "<h1>I am deleting  /h1>" + s;
    }
}
