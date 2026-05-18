package com.hpms.api;

import com.hpms.api.dto.PasswordResetRequest;
import com.hpms.api.dto.UserCreateRequest;
import com.hpms.domain.User;
import com.hpms.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Fetch all users")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch user by id")
    public User getById(@PathVariable("id") UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<User> create(@RequestBody UserCreateRequest request) {
        User created = userService.createUser(
                request.firstName(),
                request.lastName(),
                request.email(),
                request.password(),
                request.role(),
                request.wardId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{id}/password-reset")
    @Operation(summary = "Reset user password")
    public User resetPassword(@PathVariable("id") UUID id, @RequestBody PasswordResetRequest request) {
        return userService.resetPassword(id, request.newPassword());
    }

    @PostMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate user account")
    public User deactivate(@PathVariable("id") UUID id) {
        return userService.deactivateUser(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}