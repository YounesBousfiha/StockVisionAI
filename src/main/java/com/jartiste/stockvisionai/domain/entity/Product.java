package com.jartiste.stockvisionai.domain.entity;


import com.jartiste.stockvisionai.domain.enums.Unite;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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
    private String categorie;

    @NotBlank
    private BigDecimal prixVente;

    @NotBlank
    private BigDecimal prixAchat;

    @NotBlank
    private BigDecimal marge;

    @NotBlank
    private Double poids;

    @NotBlank
    private Unite unite;

    private LocalDateTime creationAt;
    private LocalDateTime updateAt;
}
