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
    public ResponseEntity<String> getAllAccounts() {

        List<Account> accounts = accountService.getAllAccounts();
        String acc = "";
        for(int i = 0; i< accounts.size();i++)
        acc += accounts.get(i).getCode() + "\n";

        return ResponseEntity.ok(acc);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<String> getAccountById(@PathVariable Long id) {
        Account account = accountService.findAccountById(id);
        return ResponseEntity.ok(account.toString());
    }



    @GetMapping
    public ResponseEntity<String> sayHello() {

        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @PostMapping("/add")
    public ResponseEntity<AccountAddResponse> addAccount(@RequestBody AccountAddRequest addRequest) {
        AccountAddResponse response = accountService.addAccount(addRequest);
        return ResponseEntity.ok().body(response);
    }

}
