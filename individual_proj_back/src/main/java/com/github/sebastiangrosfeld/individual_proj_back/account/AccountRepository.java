package com.github.sebastiangrosfeld.individual_proj_back.account;

import com.github.sebastiangrosfeld.individual_proj_back.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNumber(Long number);
    Optional<Account> findByUser(User user);
    Optional<Account> findByName(String name);
    @Query(value = "SELECT a from Account a where a.user = :user")
    List<Account> findAllAccounts(User user);
}
