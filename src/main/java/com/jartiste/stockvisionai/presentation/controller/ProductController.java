package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.ProductService;
import com.jartiste.stockvisionai.domain.exception.ResourceNotFoundException;
import com.jartiste.stockvisionai.infrastructure.service.SecurityUtils;
import com.jartiste.stockvisionai.presentation.dto.request.ProductRequest;
import com.jartiste.stockvisionai.presentation.dto.request.ProductUpdateRequest;
import com.jartiste.stockvisionai.presentation.dto.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final SecurityUtils securityUtils;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request){
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<List<ProductResponse>> getAll(){
        List<ProductResponse> allProducts = productService.findAllProducts();

        // If gestionnaire, only return products from their entrepot
        if (securityUtils.isGestionnaire()) {
            String entrepotId = securityUtils.getCurrentUserEntrepotId();
            if (entrepotId == null) {
                return ResponseEntity.ok(List.of()); // No entrepot assigned
            }
            List<ProductResponse> filteredProducts = allProducts.stream()
                    .filter(product -> entrepotId.equals(product.entrepotId()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(filteredProducts);
        }

        // Admin can see all products
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GESTIONNAIRE')")
    public ResponseEntity<ProductResponse> getOne(@PathVariable String id){
        ProductResponse product = productService.findOneProduct(id);

        // Check if gestionnaire has access to this product's entrepot
        if (securityUtils.isGestionnaire() && !securityUtils.hasAccessToEntrepot(product.entrepotId())) {
            throw new ResourceNotFoundException("Access denied: You can only access products from your assigned entrepot");
        }

        return ResponseEntity.ok(product);
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

