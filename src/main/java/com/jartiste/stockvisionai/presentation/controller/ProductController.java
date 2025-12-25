package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.ProductService;
import com.jartiste.stockvisionai.presentation.dto.request.ProductRequest;
import com.jartiste.stockvisionai.presentation.dto.request.ProductUpdateRequest;
import com.jartiste.stockvisionai.presentation.dto.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request){
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<List<ProductResponse>> getAll(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<ProductResponse> getOne(@PathVariable String id){
        return ResponseEntity.ok(productService.findOneProduct(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> partialUpdate(@PathVariable String id, @RequestBody ProductUpdateRequest request){
        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

