package com.jartiste.stockvisionai.domain.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {

    @Id
    private String id;

    @NotNull
    @Min(0)
    private Integer quantity;

    private Integer seuilAlerte;

    @NotNull
    private String productId;

    @NotNull
    private String entrepotId;
}
