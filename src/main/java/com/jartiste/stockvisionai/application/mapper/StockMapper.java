package com.jartiste.stockvisionai.application.mapper;

import com.jartiste.stockvisionai.domain.entity.Stock;
import com.jartiste.stockvisionai.presentation.dto.response.ProductResponse;
import com.jartiste.stockvisionai.presentation.dto.response.StockResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StockMapper {

    @Mapping(target = "product", ignore = true)
    StockResponse toResponse(Stock stock);

    default StockResponse toResponseWithProduct(Stock stock, ProductResponse product) {
        StockResponse base = toResponse(stock);
        if (base == null) return null;
        return new StockResponse(base.id(), base.quantity(), base.seuilAlerte(), base.productId(), base.entrepotId(), product);
    }

}
