package com.github.sebastiangrosfeld.individual_proj_back.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.sebastiangrosfeld.individual_proj_back.account.Account;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String surname;

    private String password;

    private String email;
    @Transient
    private boolean active = true;

    public User(Long id, String username, String surname, String password, String email) {
        this.id = id;
        this.username =username;
        this.surname =surname;
        this.password =password;
        this.email = email;
    }

    public User(String username, String surname, String password, String email) {
        this.username =username;
        this.surname =surname;
        this.password =password;
        this.email = email;
    }

    //private List<Account> notes;
}
