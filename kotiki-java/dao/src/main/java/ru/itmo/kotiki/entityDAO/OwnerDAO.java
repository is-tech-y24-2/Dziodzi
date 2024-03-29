package ru.itmo.kotiki.entityDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.entity.Owner;

import java.util.List;

@Repository("ownerDAO")
public interface OwnerDAO extends JpaRepository<Owner, Integer> {
    Owner getOwnerById(Integer id);
    List<Owner> findAllByOrderByIdAsc();
    List<Owner> findAllByName(String name);
}

