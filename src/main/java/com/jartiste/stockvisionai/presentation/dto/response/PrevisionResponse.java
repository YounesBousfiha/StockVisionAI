package com.jartiste.stockvisionai.presentation.dto.response;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record PrevisionResponse(
        String id,
        LocalDate predictionDate,
        String productId,
        String entrepotId,
        Integer quantity,
        Integer niveauConfiance,
        String recommendation
) {}