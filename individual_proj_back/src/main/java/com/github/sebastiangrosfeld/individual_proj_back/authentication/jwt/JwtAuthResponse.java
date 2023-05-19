package com.github.sebastiangrosfeld.individual_proj_back.authentication.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtAuthResponse {

    @NotBlank
    private String token;
    private final String tokenType = "Bearer";
}
