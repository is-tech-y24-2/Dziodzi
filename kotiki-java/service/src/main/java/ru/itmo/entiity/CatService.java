package ru.itmo.entiity;

import ru.itmo.entityDAO.CatDAO;
import ru.itmo.entityDAO.FriendshipDAO;
import ru.itmo.entityDAO.OwnerDAO;
import ru.itmo.entityDAO.OwnershipDAO;
import ru.itmo.enums.Breed;
import ru.itmo.enums.Color;
import ru.itmo.interfaces.DAO;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;

import java.sql.Timestamp;
import java.util.List;

public class CatService {
    private final DAO<Cat> catDAO;
    private final DAO<Friendship> friendshipDAO;
    private final DAO<Owner> ownerDAO;
    private final DAO<Ownership> ownershipDAO;

    public CatService(DAO<Cat> catDAO, DAO<Friendship> friendshipDAO, DAO<Owner> ownerDAO, DAO<Ownership> ownershipDAO) {
        this.catDAO = catDAO;
        this.friendshipDAO = friendshipDAO;
        this.ownerDAO = ownerDAO;
        this.ownershipDAO = ownershipDAO;
    }

    public CatService(){
        this.catDAO = new CatDAO();
        this.friendshipDAO = new FriendshipDAO();
        this.ownerDAO = new OwnerDAO();
        this.ownershipDAO = new OwnershipDAO();
    }

    public void addCat(String name, Breed breed, Color color, Timestamp birth_date) throws ServiceException {
        try {
            Cat cat = new Cat();
            cat.setName(name);
            cat.setBreed(breed);
            cat.setColor(color);
            cat.setBirthDate(birth_date);
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


    public void removeCat(Integer catId) throws ServiceException {
        try {
            Cat cat = catDAO.get(catId);
            removeOwnership(cat.getOwnerId(), catId);
            catDAO.delete(cat);
            List<Friendship> friends = getAllCatFriendships(catId);
            for (Friendship friend : friends) {
                removeFriendship(catId, friend.getFriendId());
                removeFriendship(friend.getFriendId(), catId);
            }
        } catch (DaoException daoException) {
            throw new ServiceException("Can't remove cat");
        }
    }

    public void removeFriendship(Integer catId, Integer friendId) throws ServiceException {
        try {
            this.friendshipDAO.delete(friendshipDAO.getByTwoId(catId, friendId));
        } catch (DaoException daoException) {
            throw new ServiceException("Can't remove friendship");
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

    public List<Cat> getAllCats() throws DaoException {
        return catDAO.getAll();
    }

    public List<Friendship> getAllFriendships() throws DaoException {
        return friendshipDAO.getAll();
    }

    public List<Friendship> getAllCatFriendships(Integer catId) throws DaoException {
        return friendshipDAO.getAllByOneId(catId);
    }

    public void removeOwnership(Integer ownerId, Integer catId) throws ServiceException {
        try{
            ownershipDAO.delete(ownershipDAO.getByTwoId(ownerId, catId));
            Cat cat = catDAO.get(catId);
            cat.setOwnerId(null);
            catDAO.update(cat);
        } catch (DaoException daoException) {
            throw new ServiceException("Can't remove ownership");
        }
    }
}

