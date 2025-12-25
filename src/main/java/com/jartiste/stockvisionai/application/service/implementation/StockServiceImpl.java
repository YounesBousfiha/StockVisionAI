package com.jartiste.stockvisionai.application.service.implementation;

import com.jartiste.stockvisionai.application.mapper.ProductMapper;
import com.jartiste.stockvisionai.application.mapper.StockMapper;
import com.jartiste.stockvisionai.application.service.StockService;
import com.jartiste.stockvisionai.domain.entity.Product;
import com.jartiste.stockvisionai.domain.entity.Stock;
import com.jartiste.stockvisionai.domain.exception.ResourceNotFoundException;
import com.jartiste.stockvisionai.domain.repository.EntrepotRepository;
import com.jartiste.stockvisionai.domain.repository.ProductRepository;
import com.jartiste.stockvisionai.domain.repository.StockRepository;
import com.jartiste.stockvisionai.presentation.dto.request.StockRequest;
import com.jartiste.stockvisionai.presentation.dto.request.StockUpdateRequest;
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
    private final EntrepotRepository entrepotRepository;
    private final ProductMapper productMapper;
    private final StockMapper stockMapper;
    private static final String STOCK_NOT_FOUND = "Stock not found with id: ";
    private static final String PRODUCT_NOT_FOUND = "Product not found with id: ";
    private static final String ENTREPOT_NOT_FOUND = "Entrepot not found with id: ";
    private static final String PRODUCT_ENTREPOT_MISMATCH = "Product does not belong to the specified entrepot";

    @Override
    public StockResponse createStock(String entrepotId, String productId, StockRequest request) {
        entrepotRepository.findById(entrepotId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTREPOT_NOT_FOUND + entrepotId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND + productId));

        if (!product.getEntrepotId().equals(entrepotId)) {
            throw new ResourceNotFoundException(PRODUCT_ENTREPOT_MISMATCH);
        }
        // TODO: prenvent re-creation of stock for the same product, instead increment it Quantity
        Stock stock = stockMapper.toEntity(request);
        stock.setEntrepotId(entrepotId);
        stock.setProductId(productId);

        Stock saved = stockRepository.save(stock);
        ProductResponse productResponse = productMapper.toResponse(product);
        return stockMapper.toResponseWithProduct(saved, productResponse);
    }

    @Override
    public List<StockResponse> findByEntrepotId(String entrepotId) {
        List<Stock> stocks = stockRepository.findByEntrepotId(entrepotId);
        if (stocks.isEmpty()) return List.of();

        List<String> productIds = stocks.stream().map(Stock::getProductId).distinct().toList();
        List<Product> products = productRepository.findAllById(productIds);
        Map<String, ProductResponse> productById = products.stream()
                .collect(Collectors.toMap(Product::getId, productMapper::toResponse));

        return stocks.stream()
                .map(s -> stockMapper.toResponseWithProduct(s, productById.get(s.getProductId())))
                .toList();
    }

    @Override
    public List<StockResponse> findAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        if (stocks.isEmpty()) return List.of();

        List<String> productIds = stocks.stream().map(Stock::getProductId).distinct().toList();
        List<Product> products = productRepository.findAllById(productIds);
        Map<String, ProductResponse> productById = products.stream()
                .collect(Collectors.toMap(Product::getId, productMapper::toResponse));

        return stocks.stream()
                .map(s -> stockMapper.toResponseWithProduct(s, productById.get(s.getProductId())))
                .toList();
    }

    @Override
    public StockResponse findStockById(String id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STOCK_NOT_FOUND + id));
        Product product = productRepository.findById(stock.getProductId()).orElse(null);
        ProductResponse productResponse = product != null ? productMapper.toResponse(product) : null;
        return stockMapper.toResponseWithProduct(stock, productResponse);
    }


    @Override
    public StockResponse updateStock(String id, StockUpdateRequest request) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STOCK_NOT_FOUND + id));

        stockMapper.updateStockFromUpdateDTO(request, stock);

        Stock updated = stockRepository.save(stock);
        Product product = productRepository.findById(updated.getProductId()).orElse(null);
        ProductResponse productResponse = product != null ? productMapper.toResponse(product) : null;
        return stockMapper.toResponseWithProduct(updated, productResponse);
    }

    @Override
    public void deleteStock(String id) {
        stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STOCK_NOT_FOUND + id));
        stockRepository.deleteById(id);
    }
}
