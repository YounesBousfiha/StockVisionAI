package com.jartiste.stockvisionai.application.service;

import com.jartiste.stockvisionai.presentation.dto.request.StockRequest;
import com.jartiste.stockvisionai.presentation.dto.request.StockUpdateRequest;
import com.jartiste.stockvisionai.presentation.dto.response.StockResponse;

import java.util.List;

public interface StockService {
    StockResponse createStock(String entrepotId, String productId, StockRequest request);
    List<StockResponse> findByEntrepotId(String entrepotId);
    List<StockResponse> findAllStocks();
    StockResponse findStockById(String id);
    StockResponse updateStock(String id, StockUpdateRequest request);
    void deleteStock(String id);
}
