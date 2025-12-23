package com.jartiste.stockvisionai.presentation.dto.response;

public record StockResponse(
        String id,
        Integer quantity,
        Integer seuilAlerte,
        String productId,
        String entrepotId,
        ProductResponse product
) {}

