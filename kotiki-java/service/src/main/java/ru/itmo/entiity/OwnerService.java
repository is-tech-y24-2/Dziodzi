package ru.itmo.entiity;

import ru.itmo.entityDAO.CatDAO;
import ru.itmo.entityDAO.FriendshipDAO;
import ru.itmo.entityDAO.OwnerDAO;
import ru.itmo.entityDAO.OwnershipDAO;
import ru.itmo.interfaces.DAO;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;

import java.util.List;

public class OwnerService {
    private final DAO<Cat> catDAO;
    private final DAO<Friendship> friendshipDAO;
    private final DAO<Owner> ownerDAO;
    private final DAO<Ownership> ownershipDAO;

    public OwnerService(DAO<Cat> catDAO, DAO<Friendship> friendshipDAO, DAO<Owner> ownerDAO, DAO<Ownership> ownershipDAO) {
        this.catDAO = catDAO;
        this.friendshipDAO = friendshipDAO;
        this.ownerDAO = ownerDAO;
        this.ownershipDAO = ownershipDAO;
    }

    public OwnerService(){
        this.catDAO = new CatDAO();
        this.friendshipDAO = new FriendshipDAO();
        this.ownerDAO = new OwnerDAO();
        this.ownershipDAO = new OwnershipDAO();
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
    public void removeOwner(Integer ownerId) throws ServiceException {
        try {
            Owner owner = ownerDAO.get(ownerId);
            ownerDAO.delete(owner);
            List<Ownership> owns = getAllOwnerOwnerships(ownerId);
            for(Ownership own : owns){
                removeOwnership(ownerId, own.getCatId());
            }
        } catch (DaoException daoException) {
            throw new ServiceException("Can't remove owner");
        }
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

    public void editOwnerName(Integer id, String name) throws ServiceException {
        try {
            Owner owner = ownerDAO.get(id);
            owner.setName(name);
            ownerDAO.update(owner);
        } catch (DaoException daoException) {
            throw new ServiceException("Can't edit owner's name");
        }
    }

    public List<Owner> getAllOwners() throws DaoException {
        return ownerDAO.getAll();
    }

    public List<Ownership> getAllOwnerships() throws DaoException {
        return ownershipDAO.getAll();
    }

    public Owner getCatOwner(Integer catId) throws ServiceException {
        try{
            return ownerDAO
                    .get(catDAO.get(catId).getOwnerId());
        } catch (DaoException daoException) {
            throw new ServiceException("Can't find cat's owner");
        }
    }

    public List<Ownership> getAllOwnerOwnerships(Integer ownerId) throws ServiceException, DaoException {
     return ownershipDAO.getAllByOneId(ownerId);
    }
}
