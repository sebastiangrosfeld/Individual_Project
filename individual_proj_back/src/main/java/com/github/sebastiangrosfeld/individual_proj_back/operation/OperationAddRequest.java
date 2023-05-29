package com.github.sebastiangrosfeld.individual_proj_back.operation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationAddRequest {

    private String name;

    private String type;

    private String sourceCode;

    private String destinationCode;

    private BigDecimal operationValue;


}
