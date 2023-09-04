package com.example.demo.Controller.DTO;

import com.example.demo.Model.User;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView({Details.class, AdminDetails.class})
    private long id;
    @JsonView({Details.class, AdminDetails.class})
    private String username;
    @JsonView({Details.class, AdminDetails.class})
    private String login;
    @JsonView(AdminDetails.class)
    private String password;
    @JsonView({Details.class, AdminDetails.class})
    private Byte photo;

    public interface Details{}
    public interface AdminDetails{}

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.photo = user.getPhoto();
    }
}
