package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.PrevisionService;
import com.jartiste.stockvisionai.presentation.dto.request.PrevisionRequest;
import com.jartiste.stockvisionai.presentation.dto.response.PrevisionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/previsions")
@RequiredArgsConstructor
public class PrevisionController {

    private final PrevisionService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<PrevisionResponse> create(@RequestBody PrevisionRequest request) {
        PrevisionResponse response = service.createPrevision(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<List<PrevisionResponse>> getAll() {
        return ResponseEntity.ok(service.findAllPrevision());
    }

}