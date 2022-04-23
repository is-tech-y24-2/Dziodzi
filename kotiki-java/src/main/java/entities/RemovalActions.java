package entities;


import ru.itmo.entities.ServiceImpl;
import ru.itmo.interfaces.Service;
import ru.itmo.tools.ServiceException;

import java.util.Scanner;

public class RemovalActions {

    private static Service service = new ServiceImpl();
    private static Scanner in = new Scanner(System.in);

    public static void removeCat() throws ServiceException {
        System.out.println("Enter the cat's Id:");
        Integer catId = in.nextInt();
        service.removeCat(catId);
    }

    public static void removeFriendship() throws ServiceException {
        System.out.println("Enter the cat's Id");
        Integer catId = in.nextInt();
        System.out.println("Enter the friend's Id");
        Integer friendId = in.nextInt();
        service.removeFriendship(catId, friendId);
    }

    public static void removeOwner() throws ServiceException {
        System.out.println("Enter the owner's Id");
        Integer ownerId = in.nextInt();
        service.removeOwner(ownerId);
    }

    public static void removeOwnership() throws ServiceException {
        System.out.println("Enter the owner's Id");
        Integer ownerId = in.nextInt();
        System.out.println("Enter the cat's Id");
        Integer catId = in.nextInt();
        service.removeOwnership(ownerId, catId);
    }
}
