package entity;

import interfaces.OwnerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.entity.*;
import ru.itmo.entityDAO.CatDAO;
import ru.itmo.entityDAO.FriendshipDAO;
import ru.itmo.entityDAO.OwnerDAO;
import ru.itmo.entityDAO.OwnershipDAO;
import ru.itmo.tools.ServiceException;

@RestController("ownerController")
@RequestMapping("/owners")
public class OwnerControllerImpl implements OwnerController {

    @Autowired
    @Qualifier("serviceOwnerImpl")
    private ServiceOwnerImpl serviceOwner;

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
    public String getOwnerById(@PathVariable("id") Integer id) throws ServiceException {
        StringBuilder string = new StringBuilder();
        try {
            serviceOwner.getOwnerById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        string.append(serviceOwner.getOwnerById(id).toString()).append("\n");
        return string.toString();
    }
    @Override
    @GetMapping("/getAllOwners")
    public String getAllOwners() {
        StringBuilder string = new StringBuilder();
        for(Owner owner : serviceOwner.getAllOwners()){
            string.append(owner.toString()).append("\n");
        }
        return string.toString();
    }
}
