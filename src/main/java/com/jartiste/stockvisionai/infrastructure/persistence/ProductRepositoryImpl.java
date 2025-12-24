package com.jartiste.stockvisionai.infrastructure.persistence;

import com.jartiste.stockvisionai.domain.entity.Product;
import com.jartiste.stockvisionai.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final SpringDataProductRepository repo;

    @Override
    public Product save(Product p) {
        return repo.save(p);
    }

    @Override
    public Optional<Product> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public List<Product> findAllById(Iterable<String> ids) {
        return repo.findAllById(ids);
    }
}