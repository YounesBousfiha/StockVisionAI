package com.jartiste.stockvisionai.presentation.dto.response;

import lombok.Builder;

@Builder
public record EntrePotResponse(
        String id,
        String nom,
        String address,
        String ville,
        String gestionnaireId
) {
}
