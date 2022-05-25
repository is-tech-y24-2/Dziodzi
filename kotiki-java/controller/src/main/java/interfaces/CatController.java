package interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import ru.itmo.entity.Cat;
import ru.itmo.entity.Friendship;
import ru.itmo.tools.ServiceException;

public interface CatController {
    void addCat(@RequestBody Cat cat) throws ServiceException;
    void addFriendship(@RequestBody Friendship friendship) throws ServiceException;

    void deleteCat(Integer id) throws ServiceException;
    void deleteFriendship(Integer id) throws ServiceException;

    void updateCat(Integer id) throws ServiceException;


    String getCatById(Integer id) throws ServiceException;
    String getAllCats();
    String getCatsByName(String name);
    String getCatsByBreed(@RequestBody String breed) throws ServiceException;
    String getCatsByColor(@RequestBody String color) throws ServiceException;
    String getCatsByOwnerId(Integer id) throws ServiceException;;
}
