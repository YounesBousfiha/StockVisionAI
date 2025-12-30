package com.jartiste.stockvisionai.presentation.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDTO {

    private String nom;

    private String prenom;

    @Email
    private String email;

    private String entrepotId;
}
