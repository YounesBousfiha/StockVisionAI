package com.jartiste.stockvisionai.presentation.dto.request;

import com.jartiste.stockvisionai.domain.enums.Unite;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank String nom,
        @NotBlank String description,
        @NotBlank String category,
        @NotNull BigDecimal prixVente,
        @NotNull BigDecimal prixAchat,
        @NotNull BigDecimal marge,
        @NotNull Double poids,
        @NotNull Unite unite
) {}

