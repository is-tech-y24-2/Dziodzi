package ru.itmo.kotiki.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.entity.User;
import ru.itmo.kotiki.entityDTO.UserDTO;

@Component
public class UserConverter {

    @Autowired
    public UserConverter() {
    }

    public User convertToUser(UserDTO userDto) {
        return new User(userDto.getName(), userDto.getUsername(), userDto.getPassword(), userDto.getRole());
    }
}
