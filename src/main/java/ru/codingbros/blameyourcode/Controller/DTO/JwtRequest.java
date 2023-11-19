package ru.codingbros.blameyourcode.Controller.DTO;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}
