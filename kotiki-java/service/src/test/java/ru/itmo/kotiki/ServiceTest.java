package ru.itmo.kotiki;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Owner;
import ru.itmo.kotiki.entity.ServiceCatImpl;
import ru.itmo.kotiki.entity.ServiceOwnerImpl;
import ru.itmo.kotiki.enums.Breed;
import ru.itmo.kotiki.enums.Color;
import ru.itmo.kotiki.tools.ServiceException;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceTest {
    private Session session;
    private ServiceOwnerImpl ownerService;
    private ServiceCatImpl catService;

    @Before
    public void setup() throws ServiceException {
        session = mock(Session.class);
        ownerService = mock(ServiceOwnerImpl.class);
        catService = mock(ServiceCatImpl.class);
    }

    @Test
    public void findOwnerById() throws ServiceException {
        Owner owner = new Owner("Mister Trololo");
        when(ownerService.getOwnerById(2)).thenReturn(owner);
        assertEquals(ownerService.getOwnerById(2), owner);
    }

    @Test
    public void findCatById() throws ServiceException {
        Cat cat = new Cat("Sobaka", Breed.FAT, Color.BLACK, new Timestamp(100));
        when(catService.getCatById(1)).thenReturn(cat);
        assertEquals(catService.getCatById(1), cat);
    }
}