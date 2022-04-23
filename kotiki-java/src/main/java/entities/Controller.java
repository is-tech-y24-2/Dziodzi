package entities;

import ru.itmo.entities.ServiceImpl;
import ru.itmo.interfaces.Service;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;

import java.util.Scanner;

public class Controller {
    private static Scanner in;
    private static Service service;
    public void start() throws ServiceException, DaoException {
        in = new Scanner(System.in);
        this.service = new ServiceImpl();
        boolean flag = true;
        while (flag) {
            MenuActions.showMenu();
            Integer input = in.nextInt();
            switch (input) {
                case 1:
                    AddActions.addCat();
                    break;

                case 2:
                    AddActions.addFriendship();
                    break;

                case 3:
                    AddActions.addOwner();
                    break;

                case 4:
                    AddActions.addOwnership();
                    break;

                case 5:
                    UpdateActions.updateCatName();
                    break;

                case 6:
                    UpdateActions.updateOwnerName();
                    break;

                case 7:
                    RemovalActions.removeCat();
                    break;

                case 8:
                    RemovalActions.removeFriendship();
                    break;

                case 9:
                    RemovalActions.removeOwner();
                    break;

                case 10:
                    RemovalActions.removeOwnership();
                    break;

                case 11:
                    GetActions.getAllCats();
                    break;

                case 12:
                    GetActions.getAllFriendships();
                    break;

                case 13:
                    GetActions.getAllOwners();
                    break;

                case 14:
                    GetActions.getAllOwnerships();
                    break;

                case 15:
                    GetActions.getAllCatFriendships();
                    break;

                case 16:
                    GetActions.getAllOwnerOwnerships();
                    break;

                case 17:
                    GetActions.getCatOwner();
                    break;

                case 18:
                    flag = MenuActions.shutdown();
                    break;
            }
        }
    }
}
