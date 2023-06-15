package com.github.sebastiangrosfeld.individual_proj_back.account;

import com.github.sebastiangrosfeld.individual_proj_back.auth.AuthenticationService;
import com.github.sebastiangrosfeld.individual_proj_back.user.User;
import com.github.sebastiangrosfeld.individual_proj_back.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AuthenticationService authService;

    private final UserService userService;

    public AccountAddResponse addAccount(AccountAddRequest accountAddRequest) {
        String email = authService.getEmailFromCurrentUser();
        System.out.println(email);
        String code = generateAccountNr();
        Account account = Account.builder()
                .name(accountAddRequest.getName())
                .code(code)
                .balance(BigDecimal.valueOf(100.0))
                .type(accountAddRequest.getType())
                .user(userService.getUserByEmail(email))
                .build()
        ;
        accountRepository.save(account);

        return AccountAddResponse.builder()
                .code(code)
                .userEmail(email)
                .build();
    }

    public List<Account> getAllAccountsForCurrentUser() {

        String email = authService.getEmailFromCurrentUser();

        return userService.getUserByEmail(email).getAccounts();
    }

    public List<Account> getAllAccountsForUserById(Long id){

        return userService.getUserById(id).getAccounts();
    }

    public Account findAccountById(Long id) {
        Optional<Account> account= accountRepository.findAccountById(id);

        return account.orElse(null);
       // return account.orElseThrow(() -> new IllegalStateException("Account not exist"));
    }

    public Account findAccountByName(String name) {
        return accountRepository.findAccountByName(name)
                .orElseThrow(() -> new IllegalStateException("Account not exist"));
    }

    public Account findAccountByCode(String code) {
        return accountRepository.findAccountByCode(code)
                .orElseThrow(() -> new IllegalStateException("Account not exist"));
    }

    public double getTotalBalanceForUserById(Long id){
        double total = 0.0;
        List<Account> accounts = userService.getUserById(id).getAccounts();
        for (Account a : accounts) {
            total += a.getBalance().doubleValue();
        }
        return total;
    }

    public String generateAccountNr() {
        StringBuilder generatedNr = new StringBuilder();
        Random random = new Random();

        boolean isInDatabase = true;
        String number="";

        while (isInDatabase) {

            for (int i = 0; i < 24; i++) {
                int randomNr = random.nextInt(10);
                generatedNr.append(randomNr);
            }
            number = "00" + generatedNr.toString();
            if (accountRepository.findAccountByCode(number).isEmpty()) {
               break;
            }
            generatedNr.delete(0,23);
        }
        return number;
    }
}
