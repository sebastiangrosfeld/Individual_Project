package com.github.sebastiangrosfeld.individual_proj_back.authentication.login;

import com.github.sebastiangrosfeld.individual_proj_back.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username: " + username + "not found.")));
    }
}
