package com.jartiste.stockvisionai.domain.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Entrepot {

    @Id
    private UUID uuid;

    @NotBlank
    private String nom;

    @NotBlank
    private String adresse;

    @NotBlank
    private String ville;
}
