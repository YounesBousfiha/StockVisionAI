package com.jartiste.stockvisionai.presentation.dto.request;

import com.jartiste.stockvisionai.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDTO {

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Role role;

    private String entrepotId;
}
