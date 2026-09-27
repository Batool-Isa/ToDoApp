package com.ga.ToDoApp.controller;

import com.ga.ToDoApp.model.User;
import com.ga.ToDoApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User createUser(@RequestBody User user){
        System.out.println("Controller calling create user");
        return userService.createUser(user);
    }
}
