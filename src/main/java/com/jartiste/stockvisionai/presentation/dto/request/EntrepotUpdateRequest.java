package com.jartiste.stockvisionai.presentation.dto.request;

public record EntrepotUpdateRequest(
        String nom,
        String address,
        String ville,
        String gestionnaireId
) {
}
