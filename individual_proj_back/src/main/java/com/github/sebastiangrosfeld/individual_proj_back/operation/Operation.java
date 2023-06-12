package com.github.sebastiangrosfeld.individual_proj_back.operation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sebastiangrosfeld.individual_proj_back.account.Account;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "operations")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Operation {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("operationValue")
    private BigDecimal operationValue;

    @JsonProperty("sourceCode")
    private String sourceCode;

    @JsonProperty("destinationCode")
    private String destinationCode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    public Account account;
}
