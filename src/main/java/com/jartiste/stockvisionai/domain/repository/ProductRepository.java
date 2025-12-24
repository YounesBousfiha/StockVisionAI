package com.jartiste.stockvisionai.domain.repository;

import com.jartiste.stockvisionai.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product p);
    Optional<Product> findById(String id);
    List<Product> findAllById(Iterable<String> ids);
    List<Product> findAll();
    void deleteById(String id);
}
