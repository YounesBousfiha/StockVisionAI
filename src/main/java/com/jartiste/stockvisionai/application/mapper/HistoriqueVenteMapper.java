package com.jartiste.stockvisionai.application.mapper;
import com.jartiste.stockvisionai.domain.entity.HistoriqueVente;
import com.jartiste.stockvisionai.presentation.dto.request.HistoriqueVenteRequest;
import com.jartiste.stockvisionai.presentation.dto.response.HistoriqueVenteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HistoriqueVenteMapper {
    HistoriqueVenteResponse toResponse(HistoriqueVente historiqueVente);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dayOfWeek", ignore = true)
    @Mapping(target = "month", ignore = true)
    @Mapping(target = "year", ignore = true)
    HistoriqueVente toEntity(HistoriqueVenteRequest dto);
}
