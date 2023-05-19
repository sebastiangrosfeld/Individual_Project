package com.github.sebastiangrosfeld.individual_proj_back.user;

import com.github.sebastiangrosfeld.individual_proj_back.authentication.login.MyUserDetails;
import com.github.sebastiangrosfeld.individual_proj_back.authentication.response.AuthResponse;
import com.github.sebastiangrosfeld.individual_proj_back.authentication.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal MyUserDetails userDetails) {

        return UserResponse
                .builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .surname(userDetails.getSurname())
                .email(userDetails.getEmail())
                .build();
    }

    @PostMapping("/password")
    public AuthResponse changePassword(@Valid @RequestBody PasswordRequest passwordRequest) {

        userService.changePassword(passwordRequest.getUsername(), passwordRequest.getOldPassword(), passwordRequest.getNewPassword());

        return new AuthResponse("Password changed successfully!");
    }
}
