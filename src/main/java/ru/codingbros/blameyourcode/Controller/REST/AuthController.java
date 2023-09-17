package ru.codingbros.blameyourcode.Controller.REST;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.codingbros.blameyourcode.Controller.DTO.JwtRequest;
import ru.codingbros.blameyourcode.Controller.DTO.RegistrationUserDTO;
import ru.codingbros.blameyourcode.Controller.DTO.UserDTO;
import ru.codingbros.blameyourcode.Service.UserService;
import ru.codingbros.blameyourcode.Utils.JwtTokenUtils;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return ResponseEntity.ok(userService.getAuthToken(authRequest));
    }

    @PostMapping("/registration")
    public UserDTO registrationUser(@RequestBody RegistrationUserDTO userDTO){
        return new UserDTO(userService.createUser(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getConfirmPassword()));
    }
}
