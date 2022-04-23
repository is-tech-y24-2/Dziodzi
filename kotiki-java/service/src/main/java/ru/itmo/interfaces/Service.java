package ru.itmo.interfaces;

import ru.itmo.entities.Cat;
import ru.itmo.entities.Friendship;
import ru.itmo.entities.Owner;
import ru.itmo.entities.Ownership;
import ru.itmo.enums.Breed;
import ru.itmo.enums.Color;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;
import java.util.List;

public interface Service {

    void addCat(String name, Breed breed, Color color) throws ServiceException;
    void addFriendship(Integer catId, Integer friendId) throws ServiceException;
    void addOwner(String name) throws ServiceException;
    void addOwnership(Integer ownerId, Integer catId) throws ServiceException;

    void removeCat(Integer catId) throws ServiceException;
    void removeFriendship(Integer catId, Integer friendId) throws ServiceException;
    void removeOwner(Integer ownerId) throws ServiceException;
    void removeOwnership(Integer ownerId, Integer catId) throws ServiceException;

    void editCatName(Integer id, String name) throws ServiceException;
    void editOwnerName(Integer id, String name) throws ServiceException;

    List<Cat> getAllCats() throws DaoException;

    List<Friendship> getAllFriendships() throws DaoException;
    List<Cat> getAllCatFriendships(Integer catId) throws ServiceException, DaoException;
    List<Owner> getAllOwners() throws DaoException;

    List<Ownership> getAllOwnerships() throws DaoException;
    List<Cat> getAllOwnerOwnerships(Integer ownerId) throws ServiceException, DaoException;

    Owner getCatOwner(Integer catId) throws ServiceException;
}
