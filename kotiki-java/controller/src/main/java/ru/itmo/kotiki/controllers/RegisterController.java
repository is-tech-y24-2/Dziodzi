package ru.itmo.kotiki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.entity.UserService;
import ru.itmo.kotiki.entityDTO.UserDTO;

@RestController("registerController")
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping()
    public String register(@RequestBody UserDTO user){
        try {
            userService.addUser(user);
            return "Success";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
