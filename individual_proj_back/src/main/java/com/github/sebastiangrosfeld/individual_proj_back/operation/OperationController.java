package com.github.sebastiangrosfeld.individual_proj_back.operation;


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

    @PostMapping("/add")
    public ResponseEntity<OperationAddResponse> addOperation(@RequestBody OperationAddRequest addRequest) {
        OperationAddResponse addResponse = operationService.addOperation(addRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(addResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperation(@PathVariable Long id){
        Operation operation = operationService.findAccountById(id);

        if(operation == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operation);

        return ResponseEntity.ok(operation);
    }

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


}
