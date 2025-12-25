package com.jartiste.stockvisionai.presentation.controller;
import com.jartiste.stockvisionai.application.service.HistoriqueVenteService;
import com.jartiste.stockvisionai.presentation.dto.request.HistoriqueVenteRequest;
import com.jartiste.stockvisionai.presentation.dto.response.HistoriqueVenteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/historique-vente")
@RequiredArgsConstructor
public class HistoriqueVenteController {
    private final HistoriqueVenteService historiqueVenteService;
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<HistoriqueVenteResponse> create(@RequestBody @Valid HistoriqueVenteRequest request) {
        HistoriqueVenteResponse response = historiqueVenteService.createHistoriqueVente(request);
        return ResponseEntity.status(201).body(response);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<List<HistoriqueVenteResponse>> getAll() {
        return ResponseEntity.ok(historiqueVenteService.findAllHistoriqueVentes());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<HistoriqueVenteResponse> getOne(@PathVariable String id) {
        return ResponseEntity.ok(historiqueVenteService.findOneHistoriqueVente(id));
    }
}
