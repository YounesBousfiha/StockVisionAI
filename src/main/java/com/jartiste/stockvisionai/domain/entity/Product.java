package com.jartiste.stockvisionai.domain.entity;


import com.jartiste.stockvisionai.domain.enums.Unite;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private String id;

    @NotBlank
    private String nom;

    @NotBlank
    private String description;

    @NotBlank
    private String category;

    @NotNull
    private BigDecimal prixVente;

    @NotNull
    private BigDecimal prixAchat;

    @NotNull
    private BigDecimal marge;

    @NotNull
    private Double poids;

    @NotNull
    private Unite unite;

    @NotNull
    private String entrepotId;

    private LocalDateTime creationAt;
    private LocalDateTime updateAt;
}
