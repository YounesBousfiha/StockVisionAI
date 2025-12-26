package com.jartiste.stockvisionai.application.service;

import com.jartiste.stockvisionai.infrastructure.service.PredictionService;
import com.jartiste.stockvisionai.presentation.dto.request.PrevisionRequest;
import com.jartiste.stockvisionai.presentation.dto.response.PrevisionResponse; // DTO DB
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockForecastingService {

    private final PredictionService aiService;
    private final PrevisionService dbService;

    @Transactional
    public PrevisionResponse generateAndSavePrediction(String entrepotId, String productId) {

        PrevisionResponse aiResult = aiService.predireStock(entrepotId, productId);

        PrevisionRequest request = new PrevisionRequest(
                aiResult.predictionDate(),
                productId,
                entrepotId,
                aiResult.quantity(),
                aiResult.niveauConfiance(),
                aiResult.recommendation()
        );

        return dbService.createPrevision(request);
    }
}