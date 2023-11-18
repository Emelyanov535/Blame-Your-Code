package ru.codingbros.blameyourcode.Controller.REST;

import org.postgresql.util.PSQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.codingbros.blameyourcode.Controller.DTO.JwtRequest;
import ru.codingbros.blameyourcode.Controller.DTO.JwtResponse;
import ru.codingbros.blameyourcode.Controller.DTO.RegistrationUserDTO;
import ru.codingbros.blameyourcode.Controller.DTO.UserDTO;
import ru.codingbros.blameyourcode.Service.UserService;

@RestController
@RequestMapping("/api/Account")
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/Me")
    public UserDTO getMe(){
        return new UserDTO(userService.getUser());
    }

    @PostMapping("/SignIn")
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        return userService.getAuthToken(authRequest);
    }

    @PostMapping("/SignUp")
    public UserDTO registrationUser(@RequestBody RegistrationUserDTO userDTO) {
        return new UserDTO(userService.createUser(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getConfirmPassword()));
    }

    @PutMapping("/Update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }
}
