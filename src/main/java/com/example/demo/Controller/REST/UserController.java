package com.example.demo.Controller.REST;

import com.example.demo.Controller.DTO.PostDTO;
import com.example.demo.Controller.DTO.UserDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @JsonView(UserDTO.Details.class)
    public UserDTO getUserById(@PathVariable Long id) {
        return new UserDTO(userService.findUser(id));
    }

    @PostMapping
    public MappingJacksonValue createUser(@RequestBody UserDTO userDTO){
        UserDTO createdUserDTO = new UserDTO(userService.createUser(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getPhoto()));

        MappingJacksonValue response = new MappingJacksonValue(createdUserDTO);
        response.setSerializationView(UserDTO.Details.class);

        return response;
    }

    @GetMapping("/{login}/{password}")
    public MappingJacksonValue authorizeUser(@PathVariable String login, @PathVariable String password){
        UserDTO authorizeUserDTO = new UserDTO(userService.authorizeUser(login, password));

        MappingJacksonValue response = new MappingJacksonValue(authorizeUserDTO);
        response.setSerializationView(UserDTO.Details.class);

        return response;
    }
}
