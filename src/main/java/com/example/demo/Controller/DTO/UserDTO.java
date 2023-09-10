package com.example.demo.Controller.DTO;

import com.example.demo.Model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Информация о пользователе")
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String password;
    private Byte photo;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.photo = user.getPhoto();
    }
}
