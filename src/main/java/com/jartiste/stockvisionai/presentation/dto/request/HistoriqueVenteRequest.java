package com.jartiste.stockvisionai.presentation.dto.request;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder
public record HistoriqueVenteRequest(
        @NotNull
        @Min(1)
        Integer quantity,

        @NotNull
        String productId

) {}
