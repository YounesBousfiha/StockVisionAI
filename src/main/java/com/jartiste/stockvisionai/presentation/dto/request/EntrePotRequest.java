package com.jartiste.stockvisionai.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record EntrePotRequest(
        @NotBlank
        String nom,
        @NotBlank
        String address,
        @NotBlank
        String ville,
        @NotBlank
        String gestionnaireId
) {
}
