package com.github.sebastiangrosfeld.individual_proj_back.user;


import com.github.sebastiangrosfeld.individual_proj_back.account.Account;
import com.github.sebastiangrosfeld.individual_proj_back.account.AccountRepository;
import com.github.sebastiangrosfeld.individual_proj_back.account.AccountService;
import com.github.sebastiangrosfeld.individual_proj_back.auth.AuthenticationService;
import com.github.sebastiangrosfeld.individual_proj_back.operation.Operation;
import com.github.sebastiangrosfeld.individual_proj_back.operation.OperationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RequiredArgsConstructor
@RestController
@RequestMapping("/bankapp/v1/admin")
public class AdminController {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    private final OperationService operationService;

    @GetMapping("/user_id_{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){

        if(!currentUserHasAdminRole())
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);

        User user = userService.getUserById(id);

        if(user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/account_id_{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id){

       if(!currentUserHasAdminRole())
           return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);

        Account account = accountService.findAccountById(id);

        if(account == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(account);

        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @GetMapping("/operation_id_{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable Long id){

        if(!currentUserHasAdminRole())
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);

       Operation operation = operationService.findOperationById(id);

        if(operation == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operation);

        return ResponseEntity.status(HttpStatus.OK).body(operation);
    }

    @GetMapping("/operations_for_user_id_{id}")
    public ResponseEntity<List<Operation>> getOperationsForUserId(@PathVariable Long id){

        if(!currentUserHasAdminRole())
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);

        List<Operation> operations = operationService.getAllOperationsForUserById(id);

        if(operations == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operations);

        return ResponseEntity.status(HttpStatus.OK).body(operations);
    }

    @GetMapping("/accounts_for_user_id_{id}")
    public ResponseEntity<List<Account>> getAccountsForUserId(@PathVariable Long id){

        if(!currentUserHasAdminRole())
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);

        List<Account> accounts = accountService.getAllAccountsForUserById(id);

        if(accounts == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accounts);

        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @GetMapping("/balance_for_users")
    public ResponseEntity<List<BalanceResponse>> getBalanceForUsers(){

        if(!currentUserHasAdminRole())
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);

        List<BalanceResponse> responses = new ArrayList<>();

        List<User> users = userService.getListOfUsers();
        for (User u: users) {
            responses.add(BalanceResponse.builder()
                    .email(u.getEmail())
                    .balance(accountService.getTotalBalanceForUserById(u.getId()))
                    .build());
        }

        if(responses == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responses);

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PutMapping("set_balance_{balance}_for_account_{id}")
    public ResponseEntity<?> setBalanceForCount(@PathVariable("balance") BigDecimal balance, @PathVariable("id") Long id){
        if(!currentUserHasAdminRole())
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);

        Account account = accountService.findAccountById(id);
        account.setBalance(balance);

        accountRepository.save(account);

        return ResponseEntity.ok().body(null);

    }

    private boolean currentUserHasAdminRole(){
        if(authenticationService.getRoleForCurrentUser() == Role.ADMIN)
            return true;
        return false;
    }

}
