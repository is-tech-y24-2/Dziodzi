package ru.itmo.kotiki.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import ru.itmo.kotiki.entity.Owner;
import ru.itmo.kotiki.entity.Ownership;
import ru.itmo.kotiki.entityDTO.OwnerDTO;
import ru.itmo.kotiki.tools.ServiceException;

import java.util.List;

public interface OwnerController {
    void addOwner(@RequestBody Owner owner) throws ServiceException;
    void addOwnership(@RequestBody Ownership ownership) throws ServiceException;

    void updateOwner(Integer id) throws ServiceException;

    void deleteOwner(Integer id) throws ServiceException;
    void deleteOwnership(Integer id) throws ServiceException;



    OwnerDTO getOwnerById(Integer id) throws ServiceException;
    List<OwnerDTO> getAllOwners() throws ServiceException;
}