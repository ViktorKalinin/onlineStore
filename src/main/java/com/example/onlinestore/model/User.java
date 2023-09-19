package com.example.onlinestore.model;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "user_name")
    private String userName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
