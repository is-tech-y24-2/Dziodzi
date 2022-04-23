package entities;

import ru.itmo.entities.ServiceImpl;
import ru.itmo.interfaces.Service;
import ru.itmo.tools.ServiceException;

import java.util.Scanner;

public class UpdateActions {

    private static Service service = new ServiceImpl();
    private static Scanner in = new Scanner(System.in);

    public static void updateCatName() throws ServiceException {
        System.out.println("Enter cat's Id:");
        Integer catId = in.nextInt();
        System.out.println("Enter new name:");
        String name = in.next();
        service.editCatName(catId, name);
    }

    public static void updateOwnerName() throws ServiceException {
        System.out.println("Enter owner's Id:");
        Integer ownerId = in.nextInt();
        System.out.println("Enter new name:");
        String name = in.next();
        service.editOwnerName(ownerId, name);
    }
}
