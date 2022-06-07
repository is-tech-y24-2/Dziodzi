package ru.itmo.kotiki.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.kotiki.entityDAO.UserDAO;
import ru.itmo.kotiki.entityDTO.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itmo.kotiki.tools.UserConverter;

@Service("userService")
public class UserService implements UserDetailsService {
    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    public boolean addUser(UserDTO userDTO) {
        try {
            userDAO.save(new User());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user;
        try {
            user = new UserDTO(userDAO.findByUsername(username));
        } catch (Exception e) {
            return null;
        }
        return (UserDetails) new UserConverter().convertToUser(user);
    }
}
