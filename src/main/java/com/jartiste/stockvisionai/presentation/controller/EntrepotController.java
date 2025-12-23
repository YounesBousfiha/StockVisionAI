package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.EntrePotService;
import com.jartiste.stockvisionai.presentation.dto.request.EntrePotRequest;
import com.jartiste.stockvisionai.presentation.dto.response.EntrePotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrepot")
@RequiredArgsConstructor
public class EntrepotController {


    private final EntrePotService entrePotService;


    @PostMapping()
    public ResponseEntity<EntrePotResponse> create(EntrePotRequest request) {
        EntrePotResponse response = this.entrePotService.createEntrepot(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping()
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
    public ResponseEntity<EntrePotResponse> update(@PathVariable String id) {
        EntrePotResponse response = this.entrePotService.updateEntrepot(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.entrePotService.deleteEntrepot(id);
        return ResponseEntity.noContent().build();
    }

}
