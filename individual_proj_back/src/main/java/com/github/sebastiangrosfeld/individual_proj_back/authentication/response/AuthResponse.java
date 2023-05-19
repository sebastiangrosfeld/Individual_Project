package com.github.sebastiangrosfeld.individual_proj_back.authentication.response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String message;

    public AuthResponse(String message) {
        this.message = message;
    }
}
