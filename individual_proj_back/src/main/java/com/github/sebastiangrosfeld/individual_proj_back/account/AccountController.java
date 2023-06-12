package com.github.sebastiangrosfeld.individual_proj_back.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RequiredArgsConstructor
@RestController
@RequestMapping("/bankapp/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccountsForCurrentUser() {

        List<Account> accounts = accountService.getAllForCurrentUserAccounts();

        if(accounts == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accounts);

        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.findAccountById(id);

        if(account == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(account);

        return ResponseEntity.ok(account);
    }


    @PostMapping("/add")
    public ResponseEntity<AccountAddResponse> addAccount(@RequestBody AccountAddRequest addRequest) {
        AccountAddResponse response = accountService.addAccount(addRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
