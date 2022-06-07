package ru.itmo.kotiki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.kotiki.converters.CatConverter;
import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Friendship;
import ru.itmo.kotiki.entity.CatServiceImpl;
import ru.itmo.kotiki.entityDTO.CatDTO;
import ru.itmo.kotiki.entityDTO.FriendshipDTO;
import ru.itmo.kotiki.enums.Breed;
import ru.itmo.kotiki.enums.Color;
import ru.itmo.kotiki.interfaces.CatController;
import ru.itmo.kotiki.interfaces.CatService;
import ru.itmo.kotiki.tools.ServiceException;

import java.util.List;

@RestController("catController")
@RequestMapping("/cats")
public class CatControllerImpl implements CatController {

    private final CatService serviceCat;

    private final CatConverter catConverter;

    @Autowired
    public CatControllerImpl(CatServiceImpl serviceCat, CatConverter catConverter) {
        this.serviceCat = serviceCat;
        this.catConverter = catConverter;
    }

    @Override
    @PostMapping("/addCat")
    public void addCat(@RequestBody CatDTO catDto) throws ServiceException {
        Cat cat = catConverter.convertToCat(catDto);
        try {
            serviceCat.addCat(cat);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PostMapping("/addFriendship")
    public void addFriendship(@RequestBody FriendshipDTO friendshipDto) {
        Friendship friendship = catConverter.convertToFriendship(friendshipDto);
        try {
            serviceCat.addFriendship(friendship);
            } catch (ServiceException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PatchMapping("/updateCat/{id}")
    public void updateCat(@PathVariable("id") Integer id) {
        try {
            serviceCat.updateCat(serviceCat.getCatById(id));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/deleteCat/{id}")
    public void deleteCat(@PathVariable("id") Integer id) {
       try {
           serviceCat.deleteCat(id);
       } catch (ServiceException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }
    }

    @Override
    @DeleteMapping("/deleteFriendship/{id}")
    public void deleteFriendship(@PathVariable("id") Integer id) {
        try {
        serviceCat.deleteFriendship(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/getCatById/{id}")
    public CatDTO getCatById(@PathVariable Integer id) throws ServiceException {
        try {
            serviceCat.getCatById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return catConverter.convertToCatDTO(serviceCat.getCatById(id));
    }

    @Override
    @GetMapping("/getAllCats")
    public List<CatDTO> getAllCats() {
        return catConverter.convertListOfCats(serviceCat.getAllCats());
    }

    @Override
    @GetMapping("/getCatsByName/{name}")
    public List<CatDTO> getCatsByName(@PathVariable("name") String name) {
        return catConverter.convertListOfCats(serviceCat.getAllCatsByName(name));
    }

    @Override
    @GetMapping("/getCatsByColor/{breed}")
    public List<CatDTO> getCatsByBreed(@PathVariable("breed") String breed) throws ServiceException {
        try {
            Breed.valueOf(breed);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return catConverter.convertListOfCats(serviceCat.getAllCatsByBreed(breed));
    }

    @Override
    @GetMapping("/getCatsByColor/{color}")
    public List<CatDTO> getCatsByColor(@PathVariable("color") String color) throws ServiceException {
        try {
            Color.valueOf(color);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return catConverter.convertListOfCats(serviceCat.getAllCatsByColor(color));
    }

    @Override
    @GetMapping("/getCatsByOwnerId/{id}")
    public List<CatDTO> getCatsByOwnerId(@PathVariable("id") Integer id) {
        return catConverter.convertListOfCats(serviceCat.getAllCatsByOwnerId(id));
    }
}
