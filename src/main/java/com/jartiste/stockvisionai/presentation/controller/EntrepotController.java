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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrepot")
@RequiredArgsConstructor
public class EntrepotController {


    private final EntrePotService entrePotService;
    private final StockService stockService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntrePotResponse> create(@RequestBody @Valid EntrePotRequest request) {
        EntrePotResponse response = this.entrePotService.createEntrepot(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<List<EntrePotResponse>> getAll() {
        List<EntrePotResponse> responses = this.entrePotService.getAllEntrepot();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<EntrePotResponse> getOne(@PathVariable String id) {
        EntrePotResponse response = this.entrePotService.findOneEntrepot(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/gestionnaire/{gestionnaireId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<List<EntrePotResponse>> getByGestionnaireId(@PathVariable String gestionnaireId) {
        List<EntrePotResponse> responses = this.entrePotService.findByGestionnaireId(gestionnaireId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EntrePotResponse> update(@PathVariable String id, @RequestBody @Valid EntrepotUpdateRequest request) {
        EntrePotResponse response = this.entrePotService.updateEntrepot(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.entrePotService.deleteEntrepot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/stocks")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<List<StockResponse>> getStocks(@PathVariable String id) {
        this.entrePotService.findOneEntrepot(id);
        List<StockResponse> stocks = stockService.findByEntrepotId(id);
        return ResponseEntity.ok(stocks);
    }

    @PatchMapping("/assigne/{entrepotId}")
    public ResponseEntity<EntrePotResponse> assign(
            @PathVariable String entrepotId,
            @RequestBody String gestionnaireId
    ) {
        EntrePotResponse response = this.entrePotService.assign(entrepotId, gestionnaireId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/deassign/{entrepotId}")
    public ResponseEntity<EntrePotResponse> deassign(
            @PathVariable String entrepotId
    ) {
        EntrePotResponse response = this.entrePotService.deassign(entrepotId);
        return ResponseEntity.ok(response);
    }

}
