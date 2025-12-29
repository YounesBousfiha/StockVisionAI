package com.jartiste.stockvisionai.domain.entity;


import com.jartiste.stockvisionai.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String id;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Role role;

    private boolean isActivated;

    private String entrepotId;

    private LocalDateTime creationAt;

    private LocalDateTime updateAt;

}
