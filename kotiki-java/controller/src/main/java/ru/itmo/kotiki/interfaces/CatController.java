package ru.itmo.kotiki.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Friendship;
import ru.itmo.kotiki.entityDTO.CatDTO;
import ru.itmo.kotiki.entityDTO.FriendshipDTO;
import ru.itmo.kotiki.tools.ServiceException;

import java.util.List;

public interface CatController {
    void addCat(@RequestBody CatDTO catDto) throws ServiceException;
    void addFriendship(@RequestBody FriendshipDTO friendshipDto) throws ServiceException;

    void deleteCat(Integer id) throws ServiceException;
    void deleteFriendship(Integer id) throws ServiceException;

    void updateCat(Integer id) throws ServiceException;


    CatDTO getCatById(Integer id) throws ServiceException;
    List<CatDTO> getAllCats();
    List<CatDTO> getCatsByName(String name);
    List<CatDTO> getCatsByBreed(@RequestBody String breed) throws ServiceException;
    List<CatDTO> getCatsByColor(@RequestBody String color) throws ServiceException;
    List<CatDTO> getCatsByOwnerId(Integer id) throws ServiceException;;
}
