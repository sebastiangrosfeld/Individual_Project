package com.github.sebastiangrosfeld.individual_proj_back.account;

import com.github.sebastiangrosfeld.individual_proj_back.auth.AuthenticationService;
import com.github.sebastiangrosfeld.individual_proj_back.user.UserRepository;
import com.github.sebastiangrosfeld.individual_proj_back.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        String email = authService.email;
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

    public List<Account> getAllAccounts() {
        String email = "not working"; //= authService.email;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null)
            email = authentication.getName();
        //List<Account> accounts = accountRepository.findAllAccounts(userService.getUserByEmail(email));
        System.out.println(userService.getUserByEmail(email).getAccounts());
        return userService.getUserByEmail(email).getAccounts();
    }

    public Account findAccountById(Long id) {
        Optional<Account> account= accountRepository.findAccountById(id);
                //.orElseThrow(() -> new IllegalStateException("Account not exist"));
        return account.orElseThrow(() -> new IllegalStateException("Account not exist"));
    }

    public Account findAccountByName(String name) {
        return accountRepository.findAccountByName(name)
                .orElseThrow(() -> new IllegalStateException("Account not exist"));
    }

    public Account findAccountByCode(String code) {
        return accountRepository.findAccountByCode(code)
                .orElseThrow(() -> new IllegalStateException("Account not exist"));
    }

    public String generateAccountNr() {
        StringBuilder generatedNr = new StringBuilder();
        Random random = new Random();

        boolean isInDatabase = true;

        while (isInDatabase) {

            for (int i = 0; i < 24; i++) {
                int randomNr = random.nextInt(10);
                generatedNr.append(randomNr);
            }
            String number = "00" + generatedNr.toString();
            if (!accountRepository.findAccountByCode(number).isPresent()) {
               break;
            }
            generatedNr.delete(0,23);
        }
        return  "00" + generatedNr.toString();
    }
}
