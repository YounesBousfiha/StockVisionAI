package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.infrastructure.service.PredictionService;
import com.jartiste.stockvisionai.presentation.dto.response.PredictionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/predictions")
@RequiredArgsConstructor
public class PredictionController {

    private final PredictionService predictionService;

    @PostMapping("/predict/entrepot/{entrepotId}/product/{productId}")
    public ResponseEntity<PredictionResponse> triggerPrediction(
            @PathVariable String entrepotId,
            @PathVariable String productId
    ) {
        PredictionResponse response = predictionService.predireStock(entrepotId, productId);

        return ResponseEntity.ok(response);
    }
}
