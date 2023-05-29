package com.github.sebastiangrosfeld.individual_proj_back.operation;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    private BigDecimal operationValue;

    private String sourceCode;

    private String destinationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    public Account account;
}
