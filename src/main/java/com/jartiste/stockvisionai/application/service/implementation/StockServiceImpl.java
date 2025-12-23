package com.jartiste.stockvisionai.application.service.implementation;

import com.jartiste.stockvisionai.application.mapper.ProductMapper;
import com.jartiste.stockvisionai.application.mapper.StockMapper;
import com.jartiste.stockvisionai.application.service.StockService;
import com.jartiste.stockvisionai.domain.entity.Product;
import com.jartiste.stockvisionai.domain.entity.Stock;
import com.jartiste.stockvisionai.domain.repository.ProductRepository;
import com.jartiste.stockvisionai.domain.repository.StockRepository;
import com.jartiste.stockvisionai.presentation.dto.request.StockRequest;
import com.jartiste.stockvisionai.presentation.dto.response.ProductResponse;
import com.jartiste.stockvisionai.presentation.dto.response.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final StockMapper stockMapper;

    @Override
    public StockResponse createStock(StockRequest request) {
        Stock s = Stock.builder()
                .quantity(request.quantity())
                .seuilAlerte(request.seuilAlerte())
                .productId(request.productId())
                .entrepotId(request.entrepotId())
                .build();
        Stock saved = stockRepository.save(s);
        Product product = productRepository.findById(saved.getProductId()).orElse(null);
        ProductResponse productResponse = product != null ? productMapper.toResponse(product) : null;
        return stockMapper.toResponseWithProduct(saved, productResponse);
    }

    @Override
    public List<StockResponse> findByEntrepotId(String entrepotId) {
        List<Stock> stocks = stockRepository.findByEntrepotId(entrepotId);
        if (stocks.isEmpty()) return List.of();

        List<String> productIds = stocks.stream().map(Stock::getProductId).distinct().collect(Collectors.toList());
        List<Product> products = productRepository.findAllById(productIds);
        Map<String, ProductResponse> productById = products.stream()
                .collect(Collectors.toMap(Product::getId, productMapper::toResponse));

        return stocks.stream()
                .map(s -> stockMapper.toResponseWithProduct(s, productById.get(s.getProductId())))
                .collect(Collectors.toList());
    }
}
