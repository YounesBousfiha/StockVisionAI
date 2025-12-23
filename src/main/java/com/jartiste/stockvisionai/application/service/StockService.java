package com.jartiste.stockvisionai.application.service;

import com.jartiste.stockvisionai.presentation.dto.request.StockRequest;
import com.jartiste.stockvisionai.presentation.dto.response.StockResponse;

import java.util.List;

public interface StockService {
    StockResponse createStock(StockRequest request);
    List<StockResponse> findByEntrepotId(String entrepotId);
}

