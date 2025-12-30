package com.jartiste.stockvisionai.presentation.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PrevisionRequest(
        @NotNull(message = "La date est obligatoire")
        LocalDate predictionDate,

        @NotBlank(message = "Le produit est obligatoire")
        String productId,

        @NotBlank(message = "L'entrepôt est obligatoire")
        String entrepotId,

        @NotNull(message = "La quantité est obligatoire")
        Integer quantity,

        @NotNull(message = "Le niveau de confiance est obligatoire")
        @Min(0) @Max(100)
        Integer niveauConfiance,

        @NotBlank(message = "La recommandation est obligatoire")
        String recommendation
) {}