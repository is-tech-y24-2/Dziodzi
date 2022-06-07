package ru.itmo.kotiki.entityDTO;

import ru.itmo.kotiki.entity.User;
import ru.itmo.kotiki.enums.Role;

public class UserDTO {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(User user){
        id = user.getId();
        name = user.getName();
        username = user.getUsername();
        password = user.getPassword();
        role = user.getRole();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
