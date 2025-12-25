package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.StockService;
import com.jartiste.stockvisionai.presentation.dto.request.StockRequest;
import com.jartiste.stockvisionai.presentation.dto.request.StockUpdateRequest;
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

    @PostMapping("/entrepot/{entrepot_id}/product/{product_id}")
    public ResponseEntity<StockResponse> create(
            @PathVariable("entrepot_id") String entrepotId,
            @PathVariable("product_id") String productId,
            @RequestBody @Valid StockRequest request){
        return ResponseEntity.status(201).body(stockService.createStock(entrepotId, productId, request));
    }

    @GetMapping
    public ResponseEntity<List<StockResponse>> getAll(){
        return ResponseEntity.ok(stockService.findAllStocks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponse> getOne(@PathVariable String id){
        return ResponseEntity.ok(stockService.findStockById(id));
    }

    @GetMapping("/entrepot/{entrepotId}")
    public ResponseEntity<List<StockResponse>> getByEntrepot(@PathVariable String entrepotId){
        return ResponseEntity.ok(stockService.findByEntrepotId(entrepotId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<StockResponse> partialUpdate(@PathVariable String id, @RequestBody StockUpdateRequest request){
        return ResponseEntity.ok(stockService.updateStock(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
