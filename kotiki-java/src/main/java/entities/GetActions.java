package entities;

import ru.itmo.entities.*;
import ru.itmo.interfaces.Service;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;

import java.util.List;
import java.util.Scanner;

public class GetActions {

    private static Service service = new ServiceImpl();
    private static Scanner in = new Scanner(System.in);

    public static void getAllCats() throws DaoException {
        List<Cat> cats = service.getAllCats();
        for(Cat cat : cats){
            System.out.print(cat.toString() + "\n");
        }
    }

    public static void getAllFriendships() throws DaoException {
        List<Friendship> friends = service.getAllFriendships();
        for(Friendship friendship : friends){
            System.out.print(friendship.toString() + "\n");
        }
    }

    public static void getAllOwners() throws DaoException {
        List<Owner> owners = service.getAllOwners();
        for (Owner owner : owners) {
            System.out.print(owner.toString() + "\n");
        }
    }

    public static void getAllOwnerships() throws DaoException {
        List<Ownership> ownerships = service.getAllOwnerships();
        for (Ownership ownership : ownerships) {
            System.out.print(ownership.toString() + "\n");
        }
    }


    public static void getAllCatFriendships() throws ServiceException, DaoException {
        System.out.println("Enter cat's Id");
        Integer catId = in.nextInt();
        List<Cat> cats = service.getAllCatFriendships(catId);
        for(Cat cat : cats){
            System.out.println(cat.toString());
        }
    }

    public static void getAllOwnerOwnerships() throws ServiceException, DaoException {
        System.out.println("Enter owner's Id");
        Integer ownerId = in.nextInt();
        List<Cat> cats = service.getAllOwnerOwnerships(ownerId);
        for(Cat cat : cats){
            System.out.println(cat.toString());
        }
    }

    public static void getCatOwner() throws ServiceException {
        System.out.println("Enter cat's Id");
        Integer catId = in.nextInt();
        Owner owner = service.getCatOwner(catId);
        System.out.println(owner.toString());
    }
}
