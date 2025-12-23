package com.jartiste.stockvisionai.presentation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StockRequest(
        @NotNull
        @Min(0)
        Integer quantity,
        Integer seuilAlerte,
        @NotBlank
        String productId,
        @NotBlank
        String entrepotId
) {}

