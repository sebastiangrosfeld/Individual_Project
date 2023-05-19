package com.github.sebastiangrosfeld.individual_proj_back.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PasswordRequest {
    @NotBlank(message = "Username field cannot be blank")
    @Size(min = 3, max = 40)
    private String username;

    @NotBlank(message = "Old password field cannot be blank")
    @Size(min = 3, max = 15)
    private String oldPassword;

    @NotBlank(message = "New password field cannot be blank")
    @Size(min = 3, max = 15)
    private String newPassword;
}
