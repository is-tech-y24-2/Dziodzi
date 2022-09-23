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
import ru.itmo.kotiki.interfaces.CatService;
import ru.itmo.kotiki.tools.ServiceException;

import java.util.List;

@RestController("catController")
@RequestMapping("/cats")
public class CatController {

    private final CatService catService;

    private final CatConverter catConverter;

    @Autowired
    public CatController(CatServiceImpl catService, CatConverter catConverter) {
        this.catService = catService;
        this.catConverter = catConverter;
    }

    @PostMapping("/addCat")
    public void addCat(@RequestBody CatDTO catDto) throws ServiceException {
        Cat cat = catConverter.convertToCat(catDto);
        try {
            catService.addCat(cat);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addFriendship")
    public void addFriendship(@RequestBody FriendshipDTO friendshipDto) {
        Friendship friendship = catConverter.convertToFriendship(friendshipDto);
        try {
            catService.addFriendship(friendship);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateCat/{id}")
    public void updateCat(@PathVariable("id") Integer id) {
        try {
            catService.updateCat(catService.getCatById(id));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteCat/{id}")
    public void deleteCat(@PathVariable("id") Integer id) {
        try {
            catService.deleteCat(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteFriendship/{id}")
    public void deleteFriendship(@PathVariable("id") Integer id) {
        try {
            catService.deleteFriendship(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCatById/{id}")
    public CatDTO getCatById(@PathVariable Integer id) throws ServiceException {
        try {
            catService.getCatById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return catConverter.convertToCatDTO(catService.getCatById(id));
    }

    @GetMapping("/getAllCats")
    public List<CatDTO> getAllCats() {
        return catConverter.convertListOfCats(catService.getAllCats());
    }

    @GetMapping("/getCatsByName/{name}")
    public List<CatDTO> getCatsByName(@PathVariable("name") String name) {
        return catConverter.convertListOfCats(catService.getAllCatsByName(name));
    }

    @GetMapping("/getCatsByColor/{breed}")
    public List<CatDTO> getCatsByBreed(@PathVariable("breed") String breed) throws ServiceException {
        try {
            Breed.valueOf(breed);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return catConverter.convertListOfCats(catService.getAllCatsByBreed(breed));
    }

    @GetMapping("/getCatsByColor/{color}")
    public List<CatDTO> getCatsByColor(@PathVariable("color") String color) throws ServiceException {
        try {
            Color.valueOf(color);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return catConverter.convertListOfCats(catService.getAllCatsByColor(color));
    }
    
    @GetMapping("/getCatsByOwnerId/{id}")
    public List<CatDTO> getCatsByOwnerId(@PathVariable("id") Integer id) {
        return catConverter.convertListOfCats(catService.getAllCatsByOwnerId(id));
    }
}
