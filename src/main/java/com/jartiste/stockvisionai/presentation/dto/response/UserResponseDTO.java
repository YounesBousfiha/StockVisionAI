package com.jartiste.stockvisionai.presentation.dto.response;

import com.jartiste.stockvisionai.domain.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserResponseDTO {
    private UUID uuid;
    private String nom;
    private String prenom;
    private String email;
    private Role role;
    private boolean isActivated;
    private LocalDateTime creationAt;
}
