package com.jartiste.stockvisionai.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
import java.util.UUID;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoriqueVente {

    @Id
    private String id;

    private Integer quantity;

    private DayOfWeek dayOfWeek;

    private Month month;

    private Year year;
}
