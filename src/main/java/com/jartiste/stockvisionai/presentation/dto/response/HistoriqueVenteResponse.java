package com.jartiste.stockvisionai.presentation.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record HistoriqueVenteResponse(
        String id,
        Integer quantity,
        DayOfWeek dayOfWeek,
        Month month,
        Year year
) {}
