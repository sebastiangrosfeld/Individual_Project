package com.github.sebastiangrosfeld.individual_proj_back.authentication.register;

import com.github.sebastiangrosfeld.individual_proj_back.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationRequest {

    @NotBlank(message = "Username field cannot be blank")
    @Size(min = 3, max = 40)
    private String username;

    @NotBlank(message = "Surname field cannot be blank")
    @Size(min = 3, max = 40)
    private String surname;

    @NotBlank(message = "Password field cannot be blank")
    @Size(min = 3, max = 15)
    private String password;

    @NotBlank(message = "Email field cannot be blank")
    @Size(min = 5, max = 40)
    @Email
    private String email;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, surname, passwordEncoder.encode(password), email);
    }
}
