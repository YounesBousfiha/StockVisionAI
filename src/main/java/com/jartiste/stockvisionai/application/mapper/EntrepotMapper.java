package com.jartiste.stockvisionai.application.mapper;

import com.jartiste.stockvisionai.domain.entity.Entrepot;
import com.jartiste.stockvisionai.presentation.dto.request.EntrePotRequest;
import com.jartiste.stockvisionai.presentation.dto.response.EntrePotResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface EntrepotMapper {

    EntrePotResponse toResponse(Entrepot entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    Entrepot toEntity(EntrePotRequest dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    void updateEntrepotFromDTO(EntrePotRequest dto, @MappingTarget Entrepot entity);

}
