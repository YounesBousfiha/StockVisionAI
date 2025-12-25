package com.jartiste.stockvisionai.presentation.dto.request;

import com.jartiste.stockvisionai.domain.enums.Unite;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductUpdateRequest(
        String nom,
        String description,
        String category,
        BigDecimal prixVente,
        BigDecimal prixAchat,
        BigDecimal marge,
        Double poids,
        Unite unite,
        String entrepotId
) {}
