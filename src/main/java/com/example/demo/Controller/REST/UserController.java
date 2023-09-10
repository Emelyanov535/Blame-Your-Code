package com.example.demo.Controller.REST;

import com.example.demo.Controller.DTO.UserDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return new UserDTO(userService.findUser(id));
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return new UserDTO(userService.createUser(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getPhoto()));
    }

    @GetMapping("/{login}/{password}")
    public UserDTO authorizeUser(@PathVariable String email, @PathVariable String password){
        return new UserDTO(userService.authorizeUser(email, password));
    }
}
