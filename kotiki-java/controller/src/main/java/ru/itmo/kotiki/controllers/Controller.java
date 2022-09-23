package ru.itmo.kotiki.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("Controller")
public class Controller {
    @GetMapping("/")
    public String homePage(){
        return "Welcome to Super Cats v. 2.0.2";
    }
}