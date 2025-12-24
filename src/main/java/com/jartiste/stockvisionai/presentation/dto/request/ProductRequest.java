package com.jartiste.stockvisionai.presentation.dto.request;

import com.jartiste.stockvisionai.domain.enums.Unite;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
        @NotBlank String nom,
        @NotBlank String description,
        @NotBlank String category,
        @NotNull BigDecimal prixVente,
        @NotNull BigDecimal prixAchat,
        @NotNull BigDecimal marge,
        @NotNull Double poids,
        @NotNull Unite unite,
        @NotBlank String entrepotId
) {}


