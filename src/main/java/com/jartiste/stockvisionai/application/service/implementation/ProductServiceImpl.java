package com.jartiste.stockvisionai.application.service.implementation;

import com.jartiste.stockvisionai.application.mapper.ProductMapper;
import com.jartiste.stockvisionai.application.service.ProductService;
import com.jartiste.stockvisionai.domain.entity.Product;
import com.jartiste.stockvisionai.domain.exception.ResourceNotFoundException;
import com.jartiste.stockvisionai.domain.repository.ProductRepository;
import com.jartiste.stockvisionai.presentation.dto.request.ProductRequest;
import com.jartiste.stockvisionai.presentation.dto.request.ProductUpdateRequest;
import com.jartiste.stockvisionai.presentation.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private static final String NOT_FOUND = "Product not found with id: ";

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product p = productMapper.toEntity(request);
        p.setCreationAt(LocalDateTime.now());
        p.setUpdateAt(LocalDateTime.now());
        Product saved = productRepository.save(p);
        return productMapper.toResponse(saved);
    }

    @Override
    public ProductResponse findOneProduct(String id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
        return productMapper.toResponse(p);
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse updateProduct(String id, ProductUpdateRequest request) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
        productMapper.updateProductFromUpdateDTO(request, p);
        p.setUpdateAt(LocalDateTime.now());
        Product updated = productRepository.save(p);
        return productMapper.toResponse(updated);
    }

    @Override
    public List<ProductResponse> findAllByIds(List<String> ids) {
        return productRepository.findAllById(ids).stream()
                .map(productMapper::toResponse)
                .toList();
    }
}

