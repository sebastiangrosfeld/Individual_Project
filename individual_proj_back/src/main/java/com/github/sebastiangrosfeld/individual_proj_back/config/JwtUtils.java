package com.github.sebastiangrosfeld.individual_proj_back.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private final String jwtCookie = "bankapp";

    public ResponseCookie generateJwtCookie(String jwt) {
        return ResponseCookie.from(jwtCookie, jwt).path("/bankapp").maxAge(24 * 60 * 60).httpOnly(true).build();
    }

    public ResponseCookie cleanJwtCookie() {
        return ResponseCookie.from(jwtCookie,null).path("/bankapp").maxAge(1).httpOnly(true).build();
    }
}
