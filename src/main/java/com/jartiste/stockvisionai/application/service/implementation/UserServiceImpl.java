package com.jartiste.stockvisionai.application.service.implementation;

import com.jartiste.stockvisionai.application.mapper.UserMapper;
import com.jartiste.stockvisionai.application.service.UserService;
import com.jartiste.stockvisionai.domain.entity.User;
import com.jartiste.stockvisionai.domain.exception.ResourceNotFoundException;
import com.jartiste.stockvisionai.domain.repository.UserRepository;
import com.jartiste.stockvisionai.presentation.dto.request.UserRequestDTO;
import com.jartiste.stockvisionai.presentation.dto.request.UserUpdateDTO;
import com.jartiste.stockvisionai.presentation.dto.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_NOT_FOUND = "User not found with id: ";

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActivated(true);
        user.setCreationAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND + id));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponseDTO> getUsersByEntrepotId(String entrepotId) {
        return userRepository.findByEntrepotId(entrepotId).stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(String id, UserUpdateDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND + id));

        userMapper.updateUserFromDto(request, user);
        user.setUpdateAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND + id));
        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO assignToEntrepot(String userId, String entrepotId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND + userId));

        user.setEntrepotId(entrepotId);
        user.setUpdateAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public UserResponseDTO unassignFromEntrepot(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND + userId));

        user.setEntrepotId(null);
        user.setUpdateAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }
}

