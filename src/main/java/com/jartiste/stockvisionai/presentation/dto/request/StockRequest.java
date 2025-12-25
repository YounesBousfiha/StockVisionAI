package com.jartiste.stockvisionai.presentation.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record StockRequest(
        @NotNull
        @Min(0)
        Integer quantity,
        Integer seuilAlerte
) {}

