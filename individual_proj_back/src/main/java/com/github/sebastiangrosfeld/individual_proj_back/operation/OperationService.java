package com.github.sebastiangrosfeld.individual_proj_back.operation;


import com.github.sebastiangrosfeld.individual_proj_back.account.Account;
import com.github.sebastiangrosfeld.individual_proj_back.account.AccountRepository;
import com.github.sebastiangrosfeld.individual_proj_back.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;

    private final AccountRepository accountRepository;

    private final AccountService accountService;

    public OperationAddResponse addOperation(OperationAddRequest operationAddRequest) {
        Account srcAccount = accountService.findAccountByCode(operationAddRequest.getSourceCode());
        Account destAccount = accountService.findAccountByCode(operationAddRequest.getDestinationCode());
        System.out.println(srcAccount.getCode());
        System.out.println(destAccount.getCode());

        if(srcAccount.getBalance().doubleValue() < operationAddRequest.getOperationValue().doubleValue())
            return null;

        BigDecimal srcBalance = srcAccount.getBalance().add(operationAddRequest.getOperationValue());
        BigDecimal dstBalance = destAccount.getBalance().subtract(operationAddRequest.getOperationValue());

        srcAccount.setBalance(srcBalance);
        destAccount.setBalance(dstBalance);

        accountRepository.save(srcAccount);
        accountRepository.save(destAccount);


        Operation sentOperation = Operation.builder()
                .name(operationAddRequest.getName())
                .type(operationAddRequest.getType())
                .operationValue(operationAddRequest.getOperationValue())
                .sourceCode(srcAccount.getCode())
                .destinationCode(destAccount.getCode())
                .account(srcAccount)
                .build();

        operationRepository.save(sentOperation);

        Operation getOperation = Operation.builder()
                .name(operationAddRequest.getName())
                .type(operationAddRequest.getType())
                .operationValue(operationAddRequest.getOperationValue().negate())
                .sourceCode(destAccount.getCode())
                .destinationCode(srcAccount.getCode())
                .account(destAccount)
                .build();

        operationRepository.save(getOperation);

        OperationAddResponse response = OperationAddResponse.builder()
                .sourceId(srcAccount.getId())
                .destinationId(destAccount.getId())
                .build();

        return response;
    }

    public Operation findOperationById(Long id){

        Optional<Operation> operation = operationRepository.findOperationById(id);

        return operation.orElse(null);
    }

    public List<Operation> getAllOperationsForAccount(Long id){

        return accountService.findAccountById(id).getOperations();
    }

    public List<Operation> getAllOperationsForCurrentUser(){

        List<Account> userAccounts = accountService.getAllAccountsForCurrentUser();
        List<Operation> operations = new ArrayList<>();
        for (Account a : userAccounts){
            operations.addAll(a.getOperations());
        }
        return operations;
    }

    public List<Operation> getAllOperationsForUserById(Long id){

        List<Account> userAccounts = accountService.getAllAccountsForUserById(id);
        List<Operation> operations = new ArrayList<>();
        for (Account a : userAccounts) {
            operations.addAll(a.getOperations());
        }
        return operations;
    }
}
