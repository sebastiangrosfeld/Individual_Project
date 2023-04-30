package com.github.sebastiangrosfeld.individual_proj_back.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sebastiangrosfeld.individual_proj_back.account.Account;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Table(name = "app_users")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_user_id")
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String surname;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "app_user_accounts",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> notes;
}
