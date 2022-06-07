package ru.itmo.kotiki.entityDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.enums.Breed;
import ru.itmo.kotiki.enums.Color;

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
