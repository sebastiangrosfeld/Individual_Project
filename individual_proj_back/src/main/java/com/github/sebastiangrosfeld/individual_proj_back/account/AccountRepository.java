package com.github.sebastiangrosfeld.individual_proj_back.account;

import com.github.sebastiangrosfeld.individual_proj_back.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findAccountByName(String name);

    Optional<Account> findAccountById(Long id);
    Optional<Account> findAccountByCode(String code);

    @Query(value = "SELECT a from Account a where a.user = :user")
    List<Account> findAccountsByUser(User user);

}
