package com.github.sebastiangrosfeld.individual_proj_back.account;

import com.github.sebastiangrosfeld.individual_proj_back.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findAccountByUser(User user);
    Optional<Account> findAccountByCode(String code);
}
