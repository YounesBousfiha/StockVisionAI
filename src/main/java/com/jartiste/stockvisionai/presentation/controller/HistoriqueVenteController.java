package com.jartiste.stockvisionai.presentation.controller;
import com.jartiste.stockvisionai.application.service.HistoriqueVenteService;
import com.jartiste.stockvisionai.domain.exception.ResourceNotFoundException;
import com.jartiste.stockvisionai.infrastructure.service.SecurityUtils;
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
    private final SecurityUtils securityUtils;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<HistoriqueVenteResponse> create(@RequestBody @Valid HistoriqueVenteRequest request) {
        // Check if gestionnaire has access to this entrepot
        if (securityUtils.isGestionnaire() && !securityUtils.hasAccessToEntrepot(request.entrepotId())) {
            throw new ResourceNotFoundException("Access denied: You can only create sales history in your assigned entrepot");
        }

        HistoriqueVenteResponse response = historiqueVenteService.createHistoriqueVente(request);
        return ResponseEntity.status(201).body(response);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<List<HistoriqueVenteResponse>> getAll() {
        // If gestionnaire, only return history from their entrepot
        if (securityUtils.isGestionnaire()) {
            String entrepotId = securityUtils.getCurrentUserEntrepotId();
            if (entrepotId == null) {
                return ResponseEntity.ok(List.of()); // No entrepot assigned
            }
            return ResponseEntity.ok(historiqueVenteService.findByEntrepotId(entrepotId));
        }

        // Admin can see all history
        return ResponseEntity.ok(historiqueVenteService.findAllHistoriqueVentes());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<HistoriqueVenteResponse> getOne(@PathVariable String id) {
        HistoriqueVenteResponse response = historiqueVenteService.findOneHistoriqueVente(id);

        // Check if gestionnaire has access to this history's entrepot
        if (securityUtils.isGestionnaire() && !securityUtils.hasAccessToEntrepot(response.entrepotId())) {
            throw new ResourceNotFoundException("Access denied: You can only access sales history from your assigned entrepot");
        }

        return ResponseEntity.ok(response);
    }
}
