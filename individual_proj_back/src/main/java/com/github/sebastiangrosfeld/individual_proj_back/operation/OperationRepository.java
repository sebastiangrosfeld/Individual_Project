package com.github.sebastiangrosfeld.individual_proj_back.operation;

import com.github.sebastiangrosfeld.individual_proj_back.account.Account;
import com.github.sebastiangrosfeld.individual_proj_back.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation,Long> {

    Optional<Operation> findOperationById(Long id);

}
