package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.EntrePotService;
import com.jartiste.stockvisionai.application.service.StockService;
import com.jartiste.stockvisionai.presentation.dto.request.EntrePotRequest;
import com.jartiste.stockvisionai.presentation.dto.request.EntrepotUpdateRequest;
import com.jartiste.stockvisionai.presentation.dto.response.EntrePotResponse;
import com.jartiste.stockvisionai.presentation.dto.response.StockResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrepot")
@RequiredArgsConstructor
public class EntrepotController {


    private final EntrePotService entrePotService;
    private final StockService stockService;


    @PostMapping
    public ResponseEntity<EntrePotResponse> create(@RequestBody @Valid EntrePotRequest request) {
        EntrePotResponse response = this.entrePotService.createEntrepot(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EntrePotResponse>> getAll() {
        List<EntrePotResponse> responses = this.entrePotService.getAllEntrepot();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrePotResponse> getOne(@PathVariable String id) {
        EntrePotResponse response = this.entrePotService.findOneEntrepot(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrePotResponse> update(@PathVariable String id, @RequestBody @Valid EntrepotUpdateRequest request) {
        EntrePotResponse response = this.entrePotService.updateEntrepot(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.entrePotService.deleteEntrepot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/stocks")
    public ResponseEntity<List<StockResponse>> getStocks(@PathVariable String id) {
        this.entrePotService.findOneEntrepot(id);
        List<StockResponse> stocks = stockService.findByEntrepotId(id);
        return ResponseEntity.ok(stocks);
    }

}
