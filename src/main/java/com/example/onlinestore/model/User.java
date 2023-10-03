package com.example.onlinestore.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_name")
    private String userName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String userName, String email, Role role) {
        this.userName = userName;
        this.email = email;
        this.role = role;
    }

    public User(long id, String userName, String email, Role role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.role = role;
    }
}
