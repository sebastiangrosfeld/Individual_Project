package com.github.sebastiangrosfeld.individual_proj_back.authentication.login;

import com.github.sebastiangrosfeld.individual_proj_back.authentication.jwt.JwtAuthResponse;
import com.github.sebastiangrosfeld.individual_proj_back.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/session")
    public JwtAuthResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        String token = userService.signIn(loginRequest.getUsername(), loginRequest.getPassword(), passwordEncoder);

        return new JwtAuthResponse(token);
    }
}
