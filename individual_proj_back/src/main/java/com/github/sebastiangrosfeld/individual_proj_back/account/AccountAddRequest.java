package com.github.sebastiangrosfeld.individual_proj_back.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountAddRequest {

    private String name;
    private String type;

}
