package com.jartiste.stockvisionai.presentation.dto.request;

import lombok.Builder;

@Builder
public record StockUpdateRequest(
        Integer quantity,
        Integer seuilAlerte
) {}

