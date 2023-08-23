package com.example.demo.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @Nullable
    private byte photo;

    public User(String username, String login, String password, byte photo) {
        this.username = username;
        this.login = login;
        this.password = password;
        this.photo = photo;
    }
}
