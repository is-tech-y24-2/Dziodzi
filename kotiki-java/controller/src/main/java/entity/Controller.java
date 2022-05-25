package entity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("Controller")
public class Controller {
    @GetMapping("/")
    public String homePage(){
        return "home";
    }
}