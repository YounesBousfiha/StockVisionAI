package com.jartiste.stockvisionai.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jartiste.stockvisionai.domain.enums.Unite;
import com.jartiste.stockvisionai.infrastructure.security.json.AdminAccessFilter;
import lombok.Builder;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ProductResponse(
        String id,
        String nom,
        String description,
        String category,
        BigDecimal prixVente,

        @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = AdminAccessFilter.class)
        BigDecimal prixAchat,

        @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = AdminAccessFilter.class)
        BigDecimal marge,
        Double poids,
        Unite unite,
        String entrepotId
) {}

