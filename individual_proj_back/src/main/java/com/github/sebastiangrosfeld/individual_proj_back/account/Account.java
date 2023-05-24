package com.github.sebastiangrosfeld.individual_proj_back.account;

import com.github.sebastiangrosfeld.individual_proj_back.operation.Operation;
import com.github.sebastiangrosfeld.individual_proj_back.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private BigDecimal balance;

    private String type;

    @OneToMany(mappedBy = "account")
    private List<Operation> operations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
