package entities;

import ru.itmo.entities.ServiceImpl;
import ru.itmo.enums.Breed;
import ru.itmo.enums.Color;
import ru.itmo.interfaces.Service;
import ru.itmo.tools.ServiceException;

import java.util.Scanner;

public class AddActions {
    private static Service service = new ServiceImpl();
    private static Scanner in = new Scanner(System.in);

    public static void addCat() throws ServiceException {
        System.out.println("Enter the cat's name:");
        String name = in.next();
        System.out.println("Select the cat's breed:");
        for(Breed breed : Breed.values()){
            System.out.println(breed);
        }
        Breed breed = Breed.valueOf(in.next());
        System.out.println("Select the cat's color:");
        for(Color color : Color.values()){
            System.out.println(color);
        }
        Color color = Color.valueOf(in.next());
        service.addCat(name, breed, color);
    }

    public static void addFriendship() throws ServiceException {
        System.out.println("Enter the cat's Id:");
        Integer catId = in.nextInt();
        System.out.println("Enter the friend's Id:");
        Integer friendId = in.nextInt();
        if (catId == friendId) {
            throw new ServiceException("Friendship with himself. So sad.");
        }
        service.addFriendship(catId, friendId);
    }

    public static void addOwner() throws ServiceException {
        System.out.println("Enter the owner's name");
        String name = in.next();
        service.addOwner(name);
    }

    public static void addOwnership() throws ServiceException {
        System.out.println("Enter the owner's Id");
        Integer ownerId = in.nextInt();
        System.out.println("Enter the cat's Id");
        Integer catId = in.nextInt();
        service.addOwnership(ownerId, catId);
    }
}
