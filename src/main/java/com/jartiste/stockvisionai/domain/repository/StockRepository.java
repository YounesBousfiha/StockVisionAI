package com.jartiste.stockvisionai.domain.repository;

import com.jartiste.stockvisionai.domain.entity.Stock;

import java.util.List;
import java.util.Optional;

public interface StockRepository {
    Stock save(Stock s);
    Optional<Stock> findById(String id);
    List<Stock> findByEntrepotId(String entrepotId);
    Stock findByProductId(String productIds);
    List<Stock> findAll();
    void deleteById(String id);
}

