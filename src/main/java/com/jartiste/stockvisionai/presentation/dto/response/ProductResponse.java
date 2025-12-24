package com.jartiste.stockvisionai.presentation.dto.response;

import com.jartiste.stockvisionai.domain.enums.Unite;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        String id,
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

