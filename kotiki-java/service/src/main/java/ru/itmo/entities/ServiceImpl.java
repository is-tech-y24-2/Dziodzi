package ru.itmo.entities;

import ru.itmo.entitiesDAO.CatDAO;
import ru.itmo.entitiesDAO.FriendshipDAO;
import ru.itmo.entitiesDAO.OwnerDAO;
import ru.itmo.entitiesDAO.OwnershipDAO;
import ru.itmo.enums.Breed;
import ru.itmo.enums.Color;
import ru.itmo.interfaces.DAO;
import ru.itmo.interfaces.Service;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {
    private final DAO<Cat> catDAO;
    private final DAO<Friendship> friendshipDAO;
    private final DAO<Owner> ownerDAO;
    private final DAO<Ownership> ownershipDAO;

    public ServiceImpl(DAO<Cat> catDAO, DAO<Friendship> friendshipDAO, DAO<Owner> ownerDAO, DAO<Ownership> ownershipDAO) {
        this.catDAO = catDAO;
        this.friendshipDAO = friendshipDAO;
        this.ownerDAO = ownerDAO;
        this.ownershipDAO = ownershipDAO;
    }

    public ServiceImpl(){
        this.catDAO = new CatDAO();
        this.friendshipDAO = new FriendshipDAO();
        this.ownerDAO = new OwnerDAO();
        this.ownershipDAO = new OwnershipDAO();
    }

    public void addCat(String name, Breed breed, Color color) throws ServiceException {
        try {
            Cat cat = new Cat();
            cat.setName(name);
            cat.setBreed(breed);
            cat.setColor(color);
            catDAO.add(cat);
        } catch (DaoException daoException) {
            throw new ServiceException("Can't add new cat");
        }
    }

    public void addFriendship(Integer catId, Integer friendId) throws ServiceException {
        try {
            Friendship friendship;
            friendship = new Friendship();
            friendship.setCatId(catId);
            friendship.setFriendId(friendId);
            this.friendshipDAO.add(friendship);
            friendship = new Friendship();
            friendship.setCatId(friendId);
            friendship.setFriendId(catId);
            this.friendshipDAO.add(friendship);
        } catch (DaoException daoException) {
            throw new ServiceException("Can't add new friendship");
        }
    }

    public void addOwner(String name) throws ServiceException {
        try {
            Owner owner = new Owner();
            owner.setName(name);
            ownerDAO.add(owner);
        } catch (DaoException daoException) {
            throw new ServiceException("Can't add new owner");
        }
    }

    public void addOwnership(Integer ownerId, Integer catId) throws ServiceException {
        try {
            Ownership ownership = new Ownership();
            ownership.setOwnerId(ownerId);
            ownership.setCatId(catId);
            this.ownershipDAO.add(ownership);
            Cat cat = catDAO.get(catId);
            cat.setOwnerId(ownerId);
            catDAO.update(cat);
        } catch (DaoException daoException) {
            throw new ServiceException("Can't add new ownership");
        }
    }

    public void removeCat(Integer catId) throws ServiceException {
        try {
            Cat cat = catDAO.get(catId);
            removeOwnership(cat.getOwnerId(), catId);
            catDAO.delete(cat);
            List<Cat> friends = getAllCatFriendships(catId);
            for (Cat friend : friends) {
            removeFriendship(catId, friend.getId());
            }
        } catch (DaoException daoException) {
            throw new ServiceException("Can't remove cat");
        }
    }

    public void removeFriendship(Integer catId, Integer friendId) throws ServiceException {
        try {
            Friendship friendship = this.friendshipDAO.getAll().stream()
                    .filter(item -> item.getCatId().equals(catId) && item.getFriendId().equals(friendId))
                    .findFirst().orElse(null);
            this.friendshipDAO.delete(friendship);
        } catch (DaoException daoException) {
            throw new ServiceException("Can't remove friendship");
        }
    }

    public void removeOwner(Integer ownerId) throws ServiceException {
        try {
            Owner owner = ownerDAO.get(ownerId);
            ownerDAO.delete(owner);
            List<Cat> cats = getAllOwnerOwnerships(ownerId);
            for(Cat cat : cats){
                removeOwnership(ownerId, cat.getId());
            }
        } catch (DaoException daoException) {
            throw new ServiceException("Can't remove owner");
        }
    }

    public void removeOwnership(Integer ownerId, Integer catId) throws ServiceException {
        try{
            Ownership ownership = ownershipDAO.getAll().stream()
                    .filter(item -> item.getOwnerId().equals(ownerId) && item.getCatId().equals(catId))
                    .findFirst().orElse(null);
            ownershipDAO.delete(ownership);
            Cat cat = catDAO.get(catId);
            cat.setOwnerId(null);
            catDAO.update(cat);
        } catch (DaoException daoException) {
            throw new ServiceException("Can't remove ownership");
        }
    }

    public void editCatName(Integer id, String name) throws ServiceException {
        try {
            Cat cat = catDAO.get(id);
            cat.setName(name);
            catDAO.update(cat);
        } catch (DaoException daoException) {
            throw new ServiceException("Can't edit cat's name");
        }
    }

    public void editOwnerName(Integer id, String name) throws ServiceException {
        try {
            Owner owner = ownerDAO.get(id);
            owner.setName(name);
            ownerDAO.update(owner);
        } catch (DaoException daoException) {
            throw new ServiceException("Can't edit owner's name");
        }
    }

    public List<Cat> getAllCats() throws DaoException {
        return catDAO.getAll();
    }

    public List<Friendship> getAllFriendships() throws DaoException {
        return friendshipDAO.getAll();
    }

    public List<Cat> getAllCatFriendships(Integer catId) throws DaoException {
        List<Cat> friendship = new ArrayList<>();
        for (Integer friendId : friendshipDAO.getAll().stream()
            .filter(cat -> cat.getCatId().equals(catId)).map(Friendship::getFriendId)
                .collect(Collectors.toList())) {
            friendship.add(catDAO.get(friendId));
        }
        return friendship;
    }

    public List<Owner> getAllOwners() throws DaoException {
        return ownerDAO.getAll();
    }

    public List<Ownership> getAllOwnerships() throws DaoException {
        return ownershipDAO.getAll();
    }

    public List<Cat> getAllOwnerOwnerships(Integer ownerId) throws ServiceException, DaoException {
        List<Cat> cats = new ArrayList<>();
        for(Integer catId : ownershipDAO.getAll()
            .stream().filter(item -> item.getOwnerId().equals(ownerId))
                .map(Ownership::getCatId).collect(Collectors.toList())) {
            cats.add(catDAO.get(catId));
        }
        return cats;
    }

    public Owner getCatOwner(Integer catId) throws ServiceException {
        try{
            return ownerDAO
                    .get(catDAO.get(catId).getOwnerId());
        } catch (DaoException daoException) {
            throw new ServiceException("Can't find cat's owner");
        }
    }
}
