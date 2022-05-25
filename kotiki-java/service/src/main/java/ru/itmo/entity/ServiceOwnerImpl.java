package ru.itmo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.itmo.entityDAO.CatDAO;
import ru.itmo.entityDAO.OwnerDAO;
import ru.itmo.entityDAO.OwnershipDAO;
import ru.itmo.interfaces.ServiceOwner;
import ru.itmo.tools.ServiceException;

import java.util.List;

public class ServiceOwnerImpl implements ServiceOwner {

    @Autowired
    @Qualifier("ownerDAO")
    private OwnerDAO ownerDAO;
    @Autowired
    @Qualifier("ownerAndCatDAO")
    private OwnershipDAO ownershipDAO;
    @Autowired
    @Qualifier("catDAO")
    private CatDAO catDAO;

    @Override
    public void addOwner(Owner owner) throws ServiceException {
        if (ownerDAO.existsById(owner.getId()))
            throw new ServiceException("Owner with this Id is already exist: " + owner.getId());
        ownerDAO.save(owner);
    }

    @Override
    public void addOwnership(Ownership ownership) throws ServiceException {
        if (ownershipDAO.existsById(ownership.getId()))
            throw new ServiceException("Ownership with this Id is already exist: " + ownership.getId());
        ownershipDAO.save(ownership);
        Cat cat = catDAO.getById(ownership.getCatId());
        cat.setOwnerId(ownership.getOwnerId());
        catDAO.save(cat);
    }

    @Override
    public void updateOwner(Owner owner) throws ServiceException {
        if (!ownerDAO.existsById(owner.getId()))
            throw new ServiceException("Owner with this Id doesn't exist: " + owner.getId());
        ownerDAO.save(owner);
    }

    @Override
    public void deleteOwner(Integer id) throws ServiceException {
        if (!ownerDAO.existsById(id))
            throw new ServiceException("Owner with this Id doesn't exist: " + id);
        for (Cat cat : catDAO.findAll()) {
            if (cat.getOwnerId().equals(id)) {
                cat.setOwnerId(null);
                catDAO.save(cat);
            }
        }
        for (Ownership ownership : ownershipDAO.findAll()) {
            if (ownership.getOwnerId().equals(id)) {
                ownershipDAO.delete(ownership);
            }
        }
        ownerDAO.delete(ownerDAO.getById(id));
    }

    @Override
    public void deleteOwnership(Integer id) throws ServiceException {
        if (ownershipDAO.existsById(id))
            throw new ServiceException("Ownership with this Id is already exist: " + id);
        Ownership ownership = ownershipDAO.getById(id);
        ownershipDAO.delete(ownershipDAO.getOwnershipByCatIdAndOwnerId(ownership.getOwnerId(), ownership.getCatId()));
        Cat cat = catDAO.getById(ownership.getCatId());
        cat.setOwnerId(null);
        catDAO.save(cat);
    }

    @Override
    public Owner getOwnerById(Integer id) throws ServiceException {
        if (!ownerDAO.existsById(id))
            throw new ServiceException("Owner with this Id doesn't exist: " + id);
        return ownerDAO.getOwnerById(id);
    }

    @Override
    public Owner getOwnerByCatId(Integer id) throws ServiceException {
        if (!catDAO.existsById(id))
            throw new ServiceException("Owner with this Id doesn't exist: " + id);
        return ownerDAO.getById(ownershipDAO.getOwnershipByCatId(id).getOwnerId());
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerDAO.findAllByOrderByIdAsc();
    }
}
