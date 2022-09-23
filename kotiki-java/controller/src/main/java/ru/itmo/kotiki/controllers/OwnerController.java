package ru.itmo.kotiki.controllers;

import ru.itmo.kotiki.converters.OwnerConverter;
import ru.itmo.kotiki.entityDTO.OwnerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ru.itmo.kotiki.entity.Owner;
import ru.itmo.kotiki.entity.Ownership;
import ru.itmo.kotiki.entity.OwnerServiceImpl;
import ru.itmo.kotiki.interfaces.OwnerService;
import ru.itmo.kotiki.tools.ServiceException;

import java.util.List;

@RestController("ownerController")
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    private final OwnerConverter ownerConverter;

    @Autowired
    public OwnerController(OwnerServiceImpl ownerService, OwnerConverter ownerConverter) {
        this.ownerService = ownerService;
        this.ownerConverter = ownerConverter;
    }
    
    @PostMapping("/addOwner")
    public void addOwner(@RequestBody Owner owner) {
        try {
            ownerService.addOwner(owner);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/addOwnership")
    public void addOwnership(@RequestBody Ownership ownership) {
        try {
            ownerService.addOwnership(ownership);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PatchMapping("/updateCat/{id}")
    public void updateOwner(@PathVariable("id") Integer id) {
        try {
            ownerService.updateOwner(ownerService.getOwnerById(id));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/deleteOwner/{id}")
    public void deleteOwner(@PathVariable("id") Integer id) {
        try {
            ownerService.deleteOwner(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/deleteOwnership/{id}")
    public void deleteOwnership(@PathVariable("id") Integer id) {
        try {
            ownerService.deleteOwnership(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/getOwnerById/{id}")
    public OwnerDTO getOwnerById(@PathVariable("id") Integer id) throws ServiceException {
        try {
            ownerService.getOwnerById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ownerConverter.convertToOwnerDTO(ownerService.getOwnerById(id));
    }
    
    @GetMapping("/getAllOwners")
    public List<OwnerDTO> getAllOwners() throws ServiceException {
        return ownerConverter.convertListOfOwners(ownerService.getAllOwners());
    }
}
