package com.example.backendTravel.api.controller;

import com.example.backendTravel.api.model.Role;
import com.example.backendTravel.api.model.User;
import com.example.backendTravel.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<User> userOptional = userService.findByEmail(userEmail);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            User user = userOptional.get();

            User responseUser = new User();
            responseUser.setUserId(user.getUserId());
            responseUser.setNickname(user.getNickname());
            responseUser.setUserImage(user.getUserImage());
            responseUser.setEmail(user.getEmail());
            responseUser.setRole(user.getRole());

            return ResponseEntity.ok(responseUser);
        }
    }
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<User> userOptional = userService.findByEmail(userEmail);


        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        user.setNickname(updatedUser.getNickname());
        user.setEmail(updatedUser.getEmail());

        User updatedUserData = userService.save(user);

        return ResponseEntity.ok(updatedUserData);
    }

    @GetMapping("/countUsers")
    public ResponseEntity<Long> countUsers() {
        try {
            long count = userService.countUsersByRole(Role.valueOf("USER"));
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
