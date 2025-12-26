package com.jartiste.stockvisionai.presentation.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class PredictionResponse {
    private String productId;
    private String entrepotId;
    private LocalDate datePrevision;

    private int quantitePrevue30Jours;
    private int niveauConfiance;
    private String recommandation;
}