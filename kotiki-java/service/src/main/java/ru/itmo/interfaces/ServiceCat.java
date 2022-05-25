package ru.itmo.interfaces;

import org.springframework.stereotype.Service;
import ru.itmo.entity.Cat;
import ru.itmo.entity.Friendship;
import ru.itmo.tools.ServiceException;

import java.util.List;

public interface ServiceCat {
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
