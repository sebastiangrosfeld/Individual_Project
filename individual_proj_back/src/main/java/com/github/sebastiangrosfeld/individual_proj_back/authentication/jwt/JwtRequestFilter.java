package com.github.sebastiangrosfeld.individual_proj_back.authentication.jwt;

import com.github.sebastiangrosfeld.individual_proj_back.authentication.login.MyUserDetailsService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    private final JwtTokenUtil jwtTokenUtil;

    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(jwtConfig.getHeader());

        if (authHeader != null && authHeader.startsWith(jwtConfig.getPrefix())) {

            String authToken = authHeader.replace(jwtConfig.getPrefix(), "");

            if (jwtTokenUtil.validateToken(authToken)) {
                Claims claims = jwtTokenUtil.getClaims(authToken);

                String username = claims.getSubject();

                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken usrPswdToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                usrPswdToken.setDetails(new WebAuthenticationDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usrPswdToken);
            }

            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);

            return;
        }
        return;
    }
}
