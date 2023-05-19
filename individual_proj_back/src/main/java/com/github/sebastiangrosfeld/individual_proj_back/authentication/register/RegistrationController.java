package com.github.sebastiangrosfeld.individual_proj_back.authentication.register;

import com.github.sebastiangrosfeld.individual_proj_back.authentication.response.UserResponse;
import com.github.sebastiangrosfeld.individual_proj_back.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {

        UserResponse newUser = userService.signUp(registrationRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUser.getUsername())
                .toUri();

        return ResponseEntity.created(location).body(newUser);
    }
}
