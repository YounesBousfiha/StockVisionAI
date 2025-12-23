package com.jartiste.stockvisionai.presentation.controller;

import com.jartiste.stockvisionai.application.service.ProductService;
import com.jartiste.stockvisionai.presentation.dto.request.ProductRequest;
import com.jartiste.stockvisionai.presentation.dto.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request){
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getOne(@PathVariable String id){
        return ResponseEntity.ok(productService.findOneProduct(id));
    }

}

