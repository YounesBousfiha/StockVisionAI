package com.jartiste.stockvisionai.application.service;

import com.jartiste.stockvisionai.presentation.dto.request.UserRequestDTO;
import com.jartiste.stockvisionai.presentation.dto.request.UserUpdateDTO;
import com.jartiste.stockvisionai.presentation.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO request);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(String id);
    List<UserResponseDTO> getUsersByEntrepotId(String entrepotId);
    UserResponseDTO updateUser(String id, UserUpdateDTO request);
    void deleteUser(String id);
    UserResponseDTO assignToEntrepot(String userId, String entrepotId);
    UserResponseDTO unassignFromEntrepot(String userId);
}

