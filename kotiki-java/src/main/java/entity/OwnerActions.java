package entity;

import ru.itmo.entiity.*;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;
import java.util.List;
import java.util.Scanner;

public class OwnerActions {

    private static OwnerService service = new OwnerService();
    private static Scanner in = new Scanner(System.in);


    public static void addOwner() throws ServiceException {
        System.out.println("Enter the owner's name");
        String name = in.next();
        service.addOwner(name);
    }

    public static void getAllOwners() throws DaoException {
        List<Owner> owners = service.getAllOwners();
        for (Owner owner : owners) {
            System.out.print(owner.toString() + "\n");
        }
    }
    public static void getCatOwner() throws ServiceException {
        System.out.println("Enter cat's Id");
        Integer catId = in.nextInt();
        Owner owner = service.getCatOwner(catId);
        System.out.println(owner.toString());
    }

    public static void removeOwner() throws ServiceException {
        System.out.println("Enter the owner's Id");
        Integer ownerId = in.nextInt();
        service.removeOwner(ownerId);
    }

    public static void updateOwnerName() throws ServiceException {
        System.out.println("Enter owner's Id:");
        Integer ownerId = in.nextInt();
        System.out.println("Enter new name:");
        String name = in.next();
        service.editOwnerName(ownerId, name);
    }

    public static void addOwnership() throws ServiceException {
        System.out.println("Enter the owner's Id");
        Integer ownerId = in.nextInt();
        System.out.println("Enter the cat's Id");
        Integer catId = in.nextInt();
        service.addOwnership(ownerId, catId);
    }

    public static void getAllOwnerships() throws DaoException {
        List<Ownership> ownerships = service.getAllOwnerships();
        for (Ownership ownership : ownerships) {
            System.out.print(ownership.toString() + "\n");
        }
    }
    public static void getAllOwnerOwnerships() throws ServiceException, DaoException {
        System.out.println("Enter owner's Id");
        Integer ownerId = in.nextInt();
        List<Ownership> cats = service.getAllOwnerOwnerships(ownerId);
        for(Ownership cat : cats){
            System.out.println(cat.toString());
        }
    }

    public static void removeOwnership() throws ServiceException {
        System.out.println("Enter the owner's Id");
        Integer ownerId = in.nextInt();
        System.out.println("Enter the cat's Id");
        Integer catId = in.nextInt();
        service.removeOwnership(ownerId, catId);
    }
}
