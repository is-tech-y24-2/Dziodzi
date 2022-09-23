package ru.itmo.kotiki.interfaces;

import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Friendship;
import ru.itmo.kotiki.tools.ServiceException;

import java.util.List;

public interface CatService {
    void addCat(Cat cat) throws ServiceException;
    void addFriendship(Friendship friendship) throws ServiceException;

    void updateCat(Cat cat) throws ServiceException;

    void deleteCat(Integer id) throws ServiceException;

    void deleteFriendship(Integer id) throws ServiceException;

    Cat getCatById(Integer id) throws ServiceException;
    List<Cat> getAllCats();
    List<Cat> getAllCatsByName(String name);
    List<Cat> getAllCatsByBreed(String breed) throws ServiceException;
    List<Cat> getAllCatsByColor(String color) throws ServiceException;
    List<Cat> getAllCatsByOwnerId(Integer id);
    List<Cat> getAllFriendsByCatId(Integer id);
}
