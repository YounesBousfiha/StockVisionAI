package com.jartiste.stockvisionai.application.mapper;

import com.jartiste.stockvisionai.domain.entity.Prevision;
import com.jartiste.stockvisionai.presentation.dto.request.PrevisionRequest;
import com.jartiste.stockvisionai.presentation.dto.response.PrevisionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PrevisionMapper {

    Prevision toEntity(PrevisionRequest request);

    PrevisionResponse toResponse(Prevision prevision);

}