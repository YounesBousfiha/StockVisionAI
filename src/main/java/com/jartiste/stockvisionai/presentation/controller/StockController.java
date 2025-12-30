package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.StockService;
import com.jartiste.stockvisionai.domain.exception.ResourceNotFoundException;
import com.jartiste.stockvisionai.infrastructure.service.SecurityUtils;
import com.jartiste.stockvisionai.presentation.dto.request.StockRequest;
import com.jartiste.stockvisionai.presentation.dto.request.StockUpdateRequest;
import com.jartiste.stockvisionai.presentation.dto.response.StockResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final SecurityUtils securityUtils;

    @PostMapping("/entrepot/{entrepot_id}/product/{product_id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<StockResponse> create(
            @PathVariable("entrepot_id") String entrepotId,
            @PathVariable("product_id") String productId,
            @RequestBody @Valid StockRequest request){
        // Check if gestionnaire has access to this entrepot
        if (securityUtils.isGestionnaire() && !securityUtils.hasAccessToEntrepot(entrepotId)) {
            throw new ResourceNotFoundException("Access denied: You can only create stocks in your assigned entrepot");
        }

        return ResponseEntity.status(201).body(stockService.createStock(entrepotId, productId, request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<List<StockResponse>> getAll(){
        // If gestionnaire, only return stocks from their entrepot
        if (securityUtils.isGestionnaire()) {
            String entrepotId = securityUtils.getCurrentUserEntrepotId();
            if (entrepotId == null) {
                return ResponseEntity.ok(List.of()); // No entrepot assigned
            }
            return ResponseEntity.ok(stockService.findByEntrepotId(entrepotId));
        }

        // Admin can see all stocks
        return ResponseEntity.ok(stockService.findAllStocks());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<StockResponse> getOne(@PathVariable String id){
        StockResponse stock = stockService.findStockById(id);

        // Check if gestionnaire has access to this stock's entrepot
        if (securityUtils.isGestionnaire() && !securityUtils.hasAccessToEntrepot(stock.entrepotId())) {
            throw new ResourceNotFoundException("Access denied: You can only access stocks from your assigned entrepot");
        }

        return ResponseEntity.ok(stock);
    }

    @GetMapping("/entrepot/{entrepotId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<List<StockResponse>> getByEntrepot(@PathVariable String entrepotId){
        if (securityUtils.isGestionnaire() && !securityUtils.hasAccessToEntrepot(entrepotId)) {
            throw new ResourceNotFoundException("Access denied: You can only access stocks from your assigned entrepot");
        }

        return ResponseEntity.ok(stockService.findByEntrepotId(entrepotId));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<StockResponse> partialUpdate(@PathVariable String id, @RequestBody StockUpdateRequest request){
        // First get the stock to check its entrepotId
        StockResponse existingStock = stockService.findStockById(id);

        // Check if gestionnaire has access to this stock's entrepot
        if (securityUtils.isGestionnaire() && !securityUtils.hasAccessToEntrepot(existingStock.entrepotId())) {
            throw new ResourceNotFoundException("Access denied: You can only update stocks from your assigned entrepot");
        }

        return ResponseEntity.ok(stockService.updateStock(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id){
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
