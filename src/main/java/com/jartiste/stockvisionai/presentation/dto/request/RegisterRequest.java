package com.jartiste.stockvisionai.presentation.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User registration request")
public class RegisterRequest {
    @Schema(description = "User email address", example = "user@example.com", required = true)
    private String email;

    @Schema(description = "User password (minimum 8 characters)", example = "SecurePassword123!", required = true)
    private String password;
}
