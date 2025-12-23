package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.StockService;
import com.jartiste.stockvisionai.presentation.dto.request.StockRequest;
import com.jartiste.stockvisionai.presentation.dto.response.StockResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<StockResponse> create(@RequestBody @Valid StockRequest request){
        return ResponseEntity.status(201).body(stockService.createStock(request));
    }

    @GetMapping("/entrepot/{entrepotId}")
    public ResponseEntity<List<StockResponse>> getByEntrepot(@PathVariable String entrepotId){
        return ResponseEntity.ok(stockService.findByEntrepotId(entrepotId));
    }
}

