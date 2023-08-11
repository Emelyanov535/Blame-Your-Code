package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/add/{num1}/{num2}")
    public int add(@PathVariable int num1, @PathVariable int num2) {
        return num1 + num2;
    }
}
