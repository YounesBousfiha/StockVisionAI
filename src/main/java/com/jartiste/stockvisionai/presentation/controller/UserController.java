package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.UserService;
import com.jartiste.stockvisionai.presentation.dto.request.UserRequestDTO;
import com.jartiste.stockvisionai.presentation.dto.request.UserUpdateDTO;
import com.jartiste.stockvisionai.presentation.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO request) {
        UserResponseDTO response = userService.createUser(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/entrepot/{entrepotId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getUsersByEntrepot(@PathVariable String entrepotId) {
        List<UserResponseDTO> users = userService.getUsersByEntrepotId(entrepotId);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable String id,
            @RequestBody @Valid UserUpdateDTO request) {
        UserResponseDTO user = userService.updateUser(id, request);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{userId}/assign-entrepot/{entrepotId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> assignToEntrepot(
            @PathVariable String userId,
            @PathVariable String entrepotId) {
        UserResponseDTO user = userService.assignToEntrepot(userId, entrepotId);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{userId}/unassign-entrepot")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> unassignFromEntrepot(@PathVariable String userId) {
        UserResponseDTO user = userService.unassignFromEntrepot(userId);
        return ResponseEntity.ok(user);
    }
}

