package ru.itmo.entityDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Ownership;

import java.util.List;

@Repository("ownershipDAO")
public interface OwnershipDAO extends JpaRepository<Ownership, Integer> {
    Ownership getOwnershipByCatId(Integer id);
    Ownership getOwnershipByCatIdAndOwnerId(Integer catId, Integer ownerId);
    List<Ownership> findAllByOwnerId(Integer id);
}

