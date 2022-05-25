package entity;

import interfaces.CatController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.entity.Cat;
import ru.itmo.entity.Friendship;
import ru.itmo.entity.ServiceCatImpl;
import ru.itmo.entityDAO.CatDAO;
import ru.itmo.entityDAO.FriendshipDAO;
import ru.itmo.entityDAO.OwnerDAO;
import ru.itmo.entityDAO.OwnershipDAO;
import ru.itmo.enums.Breed;
import ru.itmo.enums.Color;
import ru.itmo.tools.ServiceException;

@RestController("catController")
@RequestMapping("/cats")
public class CatControllerImpl implements CatController {

    @Autowired
    @Qualifier("serviceCatImpl")
    private ServiceCatImpl serviceCat;

    @Autowired
    @Qualifier("catDAO")
    private CatDAO catDAO;

    @Autowired
    @Qualifier("ownerDAO")
    private OwnerDAO ownerDAO;

    @Autowired
    @Qualifier("friendshipDAO")
    private FriendshipDAO friendshipDAO;

    @Autowired
    @Qualifier("ownershipDAO")
    private OwnershipDAO ownershipDAO;

    @Override
    @PostMapping("/addCat")
    public void addCat(@RequestBody Cat cat) throws ServiceException {
        try {
            serviceCat.addCat(cat);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PostMapping("/addFriendship")
    public void addFriendship(@RequestBody Friendship friendship) {
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
    public String getCatById(@PathVariable("id") Integer id) throws ServiceException {
        StringBuilder string = new StringBuilder();
        try {
            serviceCat.getCatById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        string.append(serviceCat.getCatById(id).toString()).append("\n");
        return string.toString();
    }

    @Override
    @GetMapping("/getAllCats")
    public String getAllCats() {
        StringBuilder string = new StringBuilder();
        for(Cat cat : serviceCat.getAllCats()){
            string.append(cat.toString()).append("\n");
        }
        return string.toString();
    }

    @Override
    @GetMapping("/getCatsByName/{name}")
    public String getCatsByName(@PathVariable("name") String name) {
        StringBuilder string = new StringBuilder();
        for(Cat cat : serviceCat.getAllCatsByName(name)){
            string.append(cat.toString()).append("\n");
        }
        return string.toString();
    }

    @Override
    @GetMapping("/getCatsByColor/{breed}")
    public String getCatsByBreed(@PathVariable("breed") String breed) throws ServiceException {
        try {
            Breed.valueOf(breed);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        StringBuilder string = new StringBuilder();
        for (Cat cat : serviceCat.getAllCatsByBreed(breed)){
            string.append(cat.toString()).append("\n");
        }
        return string.toString();
    }

    @Override
    @GetMapping("/getCatsByColor/{color}")
    public String getCatsByColor(@PathVariable("color") String color) throws ServiceException {
        try {
            Color.valueOf(color);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        StringBuilder string = new StringBuilder();
        for (Cat cat : serviceCat.getAllCatsByColor(color)){
            string.append(cat.toString()).append("\n");
        }
        return string.toString();
    }

    @Override
    @GetMapping("/getCatsByOwnerId/{id}")
    public String getCatsByOwnerId(@PathVariable("id") Integer id) {
        StringBuilder res = new StringBuilder();
        for (Cat cat : serviceCat.getAllCatsByOwnerId(id)){
            res.append(cat.toString()).append("\n");
        }
        return res.toString();
    }
}
