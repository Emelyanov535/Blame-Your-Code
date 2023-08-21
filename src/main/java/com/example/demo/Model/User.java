package com.example.demo.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String login;
    private String password;
    @Nullable
    private byte photo;

    public User() {
    }

    public User(String username, String login, String password, byte photo) {
        this.username = username;
        this.login = login;
        this.password = password;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getPhoto() {
        return photo;
    }

    public void setPhoto(byte photo) {
        this.photo = photo;
    }
}
