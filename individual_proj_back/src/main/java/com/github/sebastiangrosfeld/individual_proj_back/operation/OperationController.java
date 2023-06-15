package com.github.sebastiangrosfeld.individual_proj_back.operation;


import com.github.sebastiangrosfeld.individual_proj_back.account.Account;
import com.github.sebastiangrosfeld.individual_proj_back.account.AccountService;
import com.github.sebastiangrosfeld.individual_proj_back.auth.AuthenticationService;
import com.github.sebastiangrosfeld.individual_proj_back.user.User;
import com.github.sebastiangrosfeld.individual_proj_back.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RequiredArgsConstructor
@RestController
@RequestMapping("/bankapp/v1/operations")
public class OperationController {

    private final OperationService operationService;

    private final AccountService accountService;

    private final AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<OperationAddResponse> addOperation(@RequestBody OperationAddRequest addRequest) {

        if(!sourceAccountIsForCurrentUser(accountService.findAccountByCode(addRequest.getSourceCode())))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        OperationAddResponse addResponse = operationService.addOperation(addRequest);

        if(addResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(addResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(addResponse);
    }

  /*  @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperation(@PathVariable Long id){
        Operation operation = operationService.findOperationById(id);

        if(operation == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operation);

        return ResponseEntity.ok(operation);
    }*/

    @GetMapping("for_account_{id}")
    public ResponseEntity<List<Operation>> getOperationsForAccountById(@PathVariable Long id){
        List<Operation> operations = operationService.getAllOperationsForAccount(id);

        if(operations == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operations);

        return ResponseEntity.ok(operations);
    }

    @GetMapping("for_current_user")
    public ResponseEntity<List<Operation>> getOperationsForCurrentUser(){
        List<Operation> operations = operationService.getAllOperationsForCurrentUser();

        if(operations == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operations);

        return ResponseEntity.ok(operations);
    }

    private boolean sourceAccountIsForCurrentUser(Account account){

        if(account.getUser().getEmail().equals(authenticationService.getEmailFromCurrentUser()))
            return true;
        return false;
    }

}
