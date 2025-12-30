package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.StockForecastingService;
import com.jartiste.stockvisionai.presentation.dto.response.PrevisionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/predictions")
@RequiredArgsConstructor
public class PredictionController {

    private final StockForecastingService stockForecastingService;

    @PostMapping("/predict/entrepot/{entrepotId}/product/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<PrevisionResponse> triggerPrediction(
            @PathVariable String entrepotId,
            @PathVariable String productId
    ) {
        PrevisionResponse response = stockForecastingService.generateAndSavePrediction(entrepotId, productId);

        return ResponseEntity.ok(response);
    }
}
