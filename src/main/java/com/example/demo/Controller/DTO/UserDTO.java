package com.example.demo.Controller.DTO;

import com.example.demo.Model.User;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о пользователе")
public class UserDTO {
    private long id;
    private String username;
    private String login;
    private String password;
    private Byte photo;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.photo = user.getPhoto();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Byte getPhoto() {
        return photo;
    }

    public void setPhoto(Byte photo) {
        this.photo = photo;
    }
}
