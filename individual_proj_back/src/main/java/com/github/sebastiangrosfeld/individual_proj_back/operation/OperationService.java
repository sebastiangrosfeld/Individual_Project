package com.github.sebastiangrosfeld.individual_proj_back.operation;


import com.github.sebastiangrosfeld.individual_proj_back.account.Account;
import com.github.sebastiangrosfeld.individual_proj_back.account.AccountRepository;
import com.github.sebastiangrosfeld.individual_proj_back.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
}
