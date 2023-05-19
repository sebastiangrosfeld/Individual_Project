package com.github.sebastiangrosfeld.individual_proj_back.authentication.register.exception;

import com.github.sebastiangrosfeld.individual_proj_back.authentication.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class RegisterExceptionHandlerAdvice {

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<AuthResponse> handleUserExists(UserAlreadyExistsException e) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new AuthResponse(e.getMessage()));
    }
}
