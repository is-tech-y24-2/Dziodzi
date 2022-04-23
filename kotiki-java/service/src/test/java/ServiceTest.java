import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.itmo.entities.*;
import ru.itmo.enums.Breed;
import ru.itmo.interfaces.DAO;
import ru.itmo.interfaces.Service;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

public class ServiceTest {
    @Mock
    private DAO<Cat> catDAO;
    @Mock
    private DAO<Owner> ownerDAO;
    @Mock
    private DAO<Friendship> friendshipDAO;
    @Mock
    private DAO<Ownership> ownershipDAO;

    private final Service service;

    public ServiceTest() {
        MockitoAnnotations.openMocks(this);
        this.service = new ServiceImpl(catDAO, friendshipDAO, ownerDAO, ownershipDAO);
    }

    @Test
    public void createCatWithInvalidBreed_ThrowException() {
        Cat cat = new Cat();
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        { cat.setBreed(Breed.valueOf("Umpa Lumpa"));
        });
        String exceptedMessage = "No enum constant ru.itmo.enums.Breed.Umpa Lumpa";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(exceptedMessage));
    }

    @Test
    public void returnAllCats() throws DaoException {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        given(catDAO.getAll()).willReturn(Arrays.asList(cat1, cat2, cat3));
        List<Cat> cats = service.getAllCats();
        Assertions.assertEquals(cats, Arrays.asList(cat1, cat2, cat3));
    }

    @Test
    public void returnAllOwners() throws DaoException {
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();
        Owner owner3 = new Owner();
        given(ownerDAO.getAll()).willReturn(Arrays.asList(owner1, owner2, owner3));
        List<Owner> owners = service.getAllOwners();
        Assertions.assertEquals(owners, Arrays.asList(owner1, owner2, owner3));
    }

    @Test
    public void returnCatOwner() throws ServiceException, DaoException {
        Cat cat = new Cat();
        cat.setId(2);
        Owner owner = new Owner();
        owner.setId(1);
        cat.setOwnerId(1);
        given(ownerDAO.get(1)).willReturn(owner);
        given(catDAO.get(1)).willReturn(cat);
        Owner ownerOwn = service.getCatOwner(1);
        Assertions.assertEquals(ownerOwn, owner);
    }
}
