package com.github.sebastiangrosfeld.individual_proj_back.auth;

import com.github.sebastiangrosfeld.individual_proj_back.user.Role;
import com.github.sebastiangrosfeld.individual_proj_back.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role = Role.USER;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(name,surname, email ,passwordEncoder.encode(password));
    }
}
