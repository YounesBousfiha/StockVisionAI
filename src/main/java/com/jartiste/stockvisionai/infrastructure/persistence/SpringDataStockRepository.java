package com.jartiste.stockvisionai.infrastructure.persistence;

import com.jartiste.stockvisionai.domain.entity.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataStockRepository extends MongoRepository<Stock, String> {
    List<Stock> findByEntrepotId(String entrepotId);
    Stock findByProductId(String productIds);
}