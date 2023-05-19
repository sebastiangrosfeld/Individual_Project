package com.github.sebastiangrosfeld.individual_proj_back.user;

import com.github.sebastiangrosfeld.individual_proj_back.authentication.jwt.JwtTokenUtil;
import com.github.sebastiangrosfeld.individual_proj_back.authentication.register.RegistrationRequest;
import com.github.sebastiangrosfeld.individual_proj_back.authentication.register.exception.UserAlreadyExistsException;
import com.github.sebastiangrosfeld.individual_proj_back.authentication.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UserResponse signUp(RegistrationRequest registrationRequest) {

        User user = registrationRequest.toUser(passwordEncoder);

        Optional<User> userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb.isPresent()) {
            throw new UserAlreadyExistsException("User " + user.getUsername() + " already exists");
        }

        User newUser = userRepository.save(user);

        return UserResponse.builder()
                .id(newUser.getId())
                .username(newUser.getUsername())
                .surname(newUser.getSurname())
                .email(newUser.getEmail())
                .build();
    }

    public String signIn(String username, String password, PasswordEncoder passwordEncoder) {

        Optional<User> user = userRepository.login(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            return jwtTokenUtil.generateToken(authentication);
        } else {
            throw new BadCredentialsException("Invalid username or password!");
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserResponse convert(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public void changePassword(String username, String oldPassword, String newPassword) {

        Optional<User> user = userRepository.login(username);

        if (user.isPresent() && passwordEncoder.matches(oldPassword, user.get().getPassword())) {
            userRepository.updatePasswordForUser(username, passwordEncoder.encode(newPassword));
        } else {
            throw new BadCredentialsException("Invalid username or password!");
        }
    }
}
