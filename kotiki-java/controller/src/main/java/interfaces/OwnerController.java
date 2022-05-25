package interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import ru.itmo.entity.Owner;
import ru.itmo.entity.Ownership;
import ru.itmo.tools.ServiceException;

public interface OwnerController {
    void addOwner(@RequestBody Owner owner) throws ServiceException;
    void addOwnership(@RequestBody Ownership ownership) throws ServiceException;

    void updateOwner(Integer id) throws ServiceException;

    void deleteOwner(Integer id) throws ServiceException;
    void deleteOwnership(Integer id) throws ServiceException;



    String getOwnerById(Integer id) throws ServiceException;
    String getAllOwners();
}