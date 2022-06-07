package ru.itmo.kotiki.interfaces;

import ru.itmo.kotiki.entity.Owner;
import ru.itmo.kotiki.entity.Ownership;
import ru.itmo.kotiki.tools.ServiceException;

import java.util.List;

public interface ServiceOwner {
    void addOwner(Owner owner) throws ServiceException;
    void addOwnership(Ownership ownership) throws ServiceException;

    void updateOwner(Owner owner) throws ServiceException;

    void deleteOwner(Integer id) throws ServiceException;
    void deleteOwnership(Integer id) throws ServiceException;

    Owner getOwnerById(Integer id) throws ServiceException;
    Owner getOwnerByCatId(Integer id) throws ServiceException;
    List<Owner> getAllOwners() throws ServiceException;
}
