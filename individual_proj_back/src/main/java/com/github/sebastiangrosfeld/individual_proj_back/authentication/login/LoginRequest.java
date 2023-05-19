package com.github.sebastiangrosfeld.individual_proj_back.authentication.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {

    @NotBlank(message = "Username field cannot be blank")
    @Size(min = 3, max = 40)
    private String username;

    @NotBlank(message = "Password field cannot be blank")
    @Size(min = 3, max = 15)
    private String password;
}
