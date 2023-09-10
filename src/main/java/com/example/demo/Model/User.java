package com.example.demo.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "_user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @Nullable
    private byte photo;

    public User(String name, String email, String password, byte photo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }
}
