package com.jartiste.stockvisionai.infrastructure.persistence;

import com.jartiste.stockvisionai.domain.entity.Stock;
import com.jartiste.stockvisionai.domain.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepository {

    private final SpringDataStockRepository repo;

    @Override
    public Stock save(Stock s) {
        return repo.save(s);
    }

    @Override
    public Optional<Stock> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public List<Stock> findByEntrepotId(String entrepotId) {
        return repo.findByEntrepotId(entrepotId);
    }

    @Override
    public List<Stock> findByProductIdIn(List<String> productIds) {
        return repo.findByProductIdIn(productIds);
    }

    @Override
    public List<Stock> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(String id) {
        repo.deleteById(id);
    }

    @Override
    public Stock findByEntrepotIdAndProductId(String entrepotId, String productId) {
        return repo.findByEntrepotIdAndProductId(entrepotId,productId);
    }
}