package com.jartiste.stockvisionai.application.mapper;

import com.jartiste.stockvisionai.domain.entity.Product;
import com.jartiste.stockvisionai.presentation.dto.request.ProductRequest;
import com.jartiste.stockvisionai.presentation.dto.request.ProductUpdateRequest;
import com.jartiste.stockvisionai.presentation.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    Product toEntity(ProductRequest dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    void updateProductFromDTO(ProductRequest dto, @MappingTarget Product entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    void updateProductFromUpdateDTO(ProductUpdateRequest dto, @MappingTarget Product entity);
}

