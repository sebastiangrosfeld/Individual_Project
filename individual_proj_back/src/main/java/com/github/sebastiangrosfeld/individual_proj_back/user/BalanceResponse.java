package com.github.sebastiangrosfeld.individual_proj_back.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResponse {

    @JsonProperty("user_email")
    private String email;

    @JsonProperty("total_balance")
    private double balance;
}
