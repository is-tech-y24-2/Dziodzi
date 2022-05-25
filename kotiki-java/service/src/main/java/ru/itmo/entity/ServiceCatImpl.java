package ru.itmo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.itmo.entityDAO.CatDAO;
import ru.itmo.entityDAO.FriendshipDAO;
import ru.itmo.enums.Breed;
import ru.itmo.enums.Color;
import ru.itmo.interfaces.ServiceCat;
import ru.itmo.tools.ServiceException;

import java.util.ArrayList;
import java.util.List;


@Service("serviceCatImpl")
public class ServiceCatImpl implements ServiceCat {

    @Autowired
    @Qualifier("catDAO")
    private CatDAO catDAO;
    @Autowired
    @Qualifier("friendshipDAO")
    private FriendshipDAO friendshipDAO;

    @Override
    public void addCat(Cat cat) throws ServiceException {
        if (catDAO.existsById(cat.getId()))
            throw new ServiceException("Cat with this Id is already exist: " + cat.getId());
        catDAO.save(cat);
    }

    @Override
    public void addFriendship(Friendship friendship) throws ServiceException {
        if (friendshipDAO.existsById(friendship.getId()))
            throw new ServiceException("Friendship with this Id is already exist: " + friendship.getId());
        if (!catDAO.existsById(friendship.getCatId()) || !catDAO.existsById(friendship.getFriendId())){
            throw new ServiceException("Friendship with this combination can't exist" + friendship.getCatId() + ", " + friendship.getFriendId());
        }
        friendshipDAO.save(friendship);
        Integer friendId = friendship.getCatId();
        friendship.setCatId(friendship.getFriendId());
        friendship.setFriendId(friendId);
        friendshipDAO.save(friendship);
    }

    @Override
    public void updateCat(Cat cat) throws ServiceException {
        if (catDAO.existsById(cat.getId()))
            throw new ServiceException("Cat with this Id is already exist: " + cat.getId());
        catDAO.save(cat);
    }

    @Override
    public void deleteCat(Integer id) throws ServiceException {
        if (!catDAO.existsById(id)){
            throw new ServiceException("No cat to delete with this Id:" + id);
        }
        catDAO.delete(catDAO.getById(id));
    }

    @Override
    public void deleteFriendship(Integer id) throws ServiceException {
        if (!friendshipDAO.existsById(id)) {
            throw new ServiceException("No friendship to delete with this Id:" + id);
        }
            Friendship friendship = friendshipDAO.getById(id);
            friendshipDAO.delete(friendshipDAO.getFriendshipByCatIdAndFriendId(friendship.getCatId(), friendship.getFriendId()));
    }

    @Override
    public Cat getCatById(Integer id) throws ServiceException {
        if (catDAO.existsById(id)) {
            throw new ServiceException("There is no cat with this id: " + id);
        }
        return catDAO.getCatById(id);
    }

    @Override
    public List<Cat> getAllCats() {
        return catDAO.findAllByOrderByIdAsc();
    }

    @Override
    public List<Cat> getAllCatsByName(String name) {
        return catDAO.findAllByName(name);
    }

    @Override
    public List<Cat> getAllCatsByBreed(String breed) throws ServiceException {
        Breed currentBreed;
        try {
            currentBreed = Breed.valueOf(breed);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("There is no such breed: " + breed);
        }
        return catDAO.findAllByBreed(currentBreed);
    }

    @Override
    public List<Cat> getAllCatsByColor(String color) throws ServiceException {
        Color currentColor;
        try {
            currentColor = Color.valueOf(color);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("There is no such color: " + color);
        }
        return catDAO.findAllByColor(currentColor);
    }

    @Override
    public List<Cat> getAllCatsByOwnerId(Integer id) {
        return catDAO.findAllByOwnerId(id);
    }

    @Override
    public List<Cat> getAllFriendsByCatId(Integer id) {
        List<Cat> cats = new ArrayList<>();
        List <Friendship> friendshipList = friendshipDAO.findAllByCatId(id);
        for (Friendship friendship : friendshipList) {
            cats.add(catDAO.getById(friendship.getFriendId()));
        }
        return cats;
    }
}
