package com.jartiste.stockvisionai.infrastructure.persistence;

import com.jartiste.stockvisionai.domain.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataProductRepository extends MongoRepository<Product, String> {
}
