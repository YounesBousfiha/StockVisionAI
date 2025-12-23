package com.jartiste.stockvisionai.domain.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Entrepot {

    @Id
    private String id;

    @NotBlank
    private String nom;

    @NotBlank
    private String address;

    @NotBlank
    private String ville;
}
