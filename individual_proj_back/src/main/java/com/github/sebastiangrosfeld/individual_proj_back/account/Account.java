package com.github.sebastiangrosfeld.individual_proj_back.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sebastiangrosfeld.individual_proj_back.user.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Table(name = "accounts")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double balance;
    private String type;
    @JsonIgnore
    @ManyToMany(mappedBy = "notes")
    private List<AppUser> users;
}
