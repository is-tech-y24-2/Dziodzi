package ru.itmo.kotiki.entityDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.entity.User;

@Repository("userDAO")
public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
