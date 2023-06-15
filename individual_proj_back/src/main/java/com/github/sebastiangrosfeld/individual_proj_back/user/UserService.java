package com.github.sebastiangrosfeld.individual_proj_back.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public User getUserByEmail(String email){

        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User getUserById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<User> getListOfUsers() {
        return repository.findAll();
    }
}
