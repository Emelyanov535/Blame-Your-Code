package ru.codingbros.blameyourcode.Controller.REST;

import lombok.AllArgsConstructor;
import ru.codingbros.blameyourcode.Controller.DTO.UserDTO;
import ru.codingbros.blameyourcode.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return new UserDTO(userService.findUser(id));
    }
}