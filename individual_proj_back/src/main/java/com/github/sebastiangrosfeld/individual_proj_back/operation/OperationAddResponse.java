package com.github.sebastiangrosfeld.individual_proj_back.operation;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationAddResponse {

    @JsonProperty("source_account_id")
    private Long sourceId;

    @JsonProperty("destination_account_id")
    private Long destinationId;
}
