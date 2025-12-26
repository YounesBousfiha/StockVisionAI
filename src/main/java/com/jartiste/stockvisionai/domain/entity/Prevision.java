package com.jartiste.stockvisionai.domain.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prevision {

    @Id
    private String id;

    @NotBlank
    private LocalDate predictionDate;

    private String productId;
    private String entrepotId;

    @NotBlank
    private Integer quantity;

    @NotBlank
    private Integer niveauConfiance;

    @NotBlank
    private String recommendation;

}
