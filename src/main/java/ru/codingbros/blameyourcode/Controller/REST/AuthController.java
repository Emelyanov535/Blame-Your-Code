package ru.codingbros.blameyourcode.Controller.REST;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.codingbros.blameyourcode.Controller.DTO.JwtRequest;
import ru.codingbros.blameyourcode.Controller.DTO.JwtResponse;
import ru.codingbros.blameyourcode.Controller.DTO.RegistrationUserDTO;
import ru.codingbros.blameyourcode.Controller.DTO.UserDTO;
import ru.codingbros.blameyourcode.Service.UserService;
import ru.codingbros.blameyourcode.Utils.JwtTokenUtils;

@RestController
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return null;
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/registration")
    public UserDTO registrationUser(@RequestBody RegistrationUserDTO userDTO){
        return new UserDTO(userService.createUser(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getConfirmPassword()));
    }
}
