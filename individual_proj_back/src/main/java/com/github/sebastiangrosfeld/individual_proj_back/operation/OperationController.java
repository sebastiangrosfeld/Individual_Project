package com.github.sebastiangrosfeld.individual_proj_back.operation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RequiredArgsConstructor
@RestController
@RequestMapping("/bankapp/v1/operations")
public class OperationController {

    private final OperationService operationService;

    @PostMapping("/add")
    public ResponseEntity<OperationAddResponse> addOperation(@RequestBody OperationAddRequest addRequest) {
        OperationAddResponse addResponse = operationService.addOperation(addRequest);
        return ResponseEntity.ok().body(addResponse);
    }
}
