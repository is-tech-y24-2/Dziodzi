package ru.itmo.entityDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.entity.Cat;
import ru.itmo.enums.Breed;
import ru.itmo.enums.Color;

import java.util.List;

@Repository("catDAO")
public interface CatDAO extends JpaRepository<Cat, Integer> {

    Cat getCatById(Integer id);
    List<Cat> findAllByOrderByIdAsc();
    List<Cat> findAllByColor(Color color);
    List<Cat> findAllByOwnerId(Integer ownerId);
    List<Cat> findAllByBreed(Breed breed);
    List<Cat> findAllByName(String name);
}
