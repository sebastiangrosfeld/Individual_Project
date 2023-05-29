package com.github.sebastiangrosfeld.individual_proj_back.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountAddResponse {

    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("code")
    private String code;
}
