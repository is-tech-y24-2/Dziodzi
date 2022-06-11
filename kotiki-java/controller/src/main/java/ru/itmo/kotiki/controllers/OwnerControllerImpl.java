package ru.itmo.kotiki.controllers;

import ru.itmo.kotiki.converters.OwnerConverter;
import ru.itmo.kotiki.entityDTO.OwnerDTO;
import ru.itmo.kotiki.interfaces.OwnerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ru.itmo.kotiki.entity.Owner;
import ru.itmo.kotiki.entity.Ownership;
import ru.itmo.kotiki.entity.ServiceOwnerImpl;
import ru.itmo.kotiki.interfaces.ServiceOwner;
import ru.itmo.kotiki.tools.ServiceException;

import java.util.List;

@RestController("ownerController")
@RequestMapping("/owners")
public class OwnerControllerImpl implements OwnerController {

    private final ServiceOwner serviceOwner;

    private final OwnerConverter ownerConverter;

    @Autowired
    public OwnerControllerImpl(ServiceOwnerImpl serviceOwner, OwnerConverter ownerConverter) {
        this.serviceOwner = serviceOwner;
        this.ownerConverter = ownerConverter;
    }


    @Override
    @PostMapping("/addOwner")
    public void addOwner(@RequestBody Owner owner){
        try {
            serviceOwner.addOwner(owner);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PostMapping("/addOwnership")
    public void addOwnership(@RequestBody Ownership ownership) {
        try {
            serviceOwner.addOwnership(ownership);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PatchMapping("/updateCat/{id}")
    public void updateOwner(@PathVariable("id") Integer id) {
        try {
            serviceOwner.updateOwner(serviceOwner.getOwnerById(id));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/deleteOwner/{id}")
    public void deleteOwner(@PathVariable("id") Integer id) {
        try {
            serviceOwner.deleteOwner(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/deleteOwnership/{id}")
    public void deleteOwnership(@PathVariable("id") Integer id) {
        try {
            serviceOwner.deleteOwnership(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/getOwnerById/{id}")
    public OwnerDTO getOwnerById(@PathVariable("id") Integer id) throws ServiceException {
        try {
            serviceOwner.getOwnerById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ownerConverter.convertToOwnerDTO(serviceOwner.getOwnerById(id));
    }
    @Override
    @GetMapping("/getAllOwners")
    public List<OwnerDTO> getAllOwners() throws ServiceException {
        return ownerConverter.convertListOfOwners(serviceOwner.getAllOwners());
    }
}
