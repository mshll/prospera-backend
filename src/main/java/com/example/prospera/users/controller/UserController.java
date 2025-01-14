package com.example.prospera.users.controller;

import com.example.prospera.property.entity.PropertyEntity;
import com.example.prospera.users.UserEntity;
import com.example.prospera.users.bo.RegisterRequest;
import com.example.prospera.users.bo.UserResponse;
import com.example.prospera.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> allUsers() {
        List<UserEntity> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/me/update")
    public ResponseEntity<UserResponse> updateUser(@RequestBody RegisterRequest request) {
        try {
            UserResponse updatedUser = userService.updateUser(request);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        try {
            UserResponse userResponse = userService.getUserById(id);
            return ResponseEntity.ok(userResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User has been successfully deleted.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/like/{id}")
    public ResponseEntity<String> likeProperty(@PathVariable Long id) {
        try {
            String message = userService.likeProperty(id);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/unlike/{id}")
    public ResponseEntity<String> unlikeProperty(@PathVariable Long id) {
        try {
            String message = userService.unlikeProperty(id);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/likes")
    public ResponseEntity<List<PropertyEntity>> getLikedProperties() {
        List<PropertyEntity> likedProperties = userService.getLikedProperties();
        return ResponseEntity.ok(likedProperties);
    }

}
