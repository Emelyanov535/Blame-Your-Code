package ru.codingbros.blameyourcode.Controller.DTO;

import lombok.Data;

@Data
public class RegistrationUserDTO {
    private String username;
    private String password;
    private String email;
    private String confirmPassword;
}
