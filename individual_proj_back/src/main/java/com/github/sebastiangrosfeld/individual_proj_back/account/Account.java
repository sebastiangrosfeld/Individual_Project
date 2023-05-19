package com.github.sebastiangrosfeld.individual_proj_back.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sebastiangrosfeld.individual_proj_back.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "accounts")
@Entity(name = "Account")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long number;
    private BigDecimal balance;
    private String type;
    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "id"
    )
    private User user;

}
