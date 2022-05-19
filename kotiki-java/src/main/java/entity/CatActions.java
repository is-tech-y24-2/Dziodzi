package entity;

import ru.itmo.entiity.Cat;
import ru.itmo.entiity.CatService;
import ru.itmo.entiity.Friendship;
import ru.itmo.enums.Breed;
import ru.itmo.enums.Color;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class CatActions {

    private static CatService service = new CatService();
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
        Timestamp birth_date = new Timestamp(System.currentTimeMillis());
        service.addCat(name, breed, color, birth_date);
    }

    public static void getAllCats() throws DaoException {
        List<Cat> cats = service.getAllCats();
        for(Cat cat : cats){
            System.out.print(cat.toString() + "\n");
        }
    }

    public static void removeCat() throws ServiceException {
        System.out.println("Enter the cat's Id:");
        Integer catId = in.nextInt();
        service.removeCat(catId);
    }

    public static void updateCatName() throws ServiceException {
        System.out.println("Enter cat's Id:");
        Integer catId = in.nextInt();
        System.out.println("Enter new name:");
        String name = in.next();
        service.editCatName(catId, name);
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

    public static void getAllCatFriendships() throws ServiceException, DaoException {
        System.out.println("Enter cat's Id");
        Integer catId = in.nextInt();
        List<Friendship> cats = service.getAllCatFriendships(catId);
        for (Friendship cat : cats) {
            System.out.println(cat.toString());
        }
    }

        public static void getAllFriendships() throws DaoException {
            List<Friendship> friends = service.getAllFriendships();
            for(Friendship friendship : friends){
                System.out.print(friendship.toString() + "\n");
            }
        }

    public static void removeFriendship() throws ServiceException {
        System.out.println("Enter the cat's Id");
        Integer catId = in.nextInt();
        System.out.println("Enter the friend's Id");
        Integer friendId = in.nextInt();
        service.removeFriendship(catId, friendId);
    }

}
