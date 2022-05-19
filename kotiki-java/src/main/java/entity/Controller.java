package entity;

import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;

import java.util.Scanner;

public class Controller {
    private static Scanner in;
    private static CatActions catActions;
    private static OwnerActions ownerActions;
    public void start() throws ServiceException, DaoException {
        in = new Scanner(System.in);
        this.catActions = new CatActions();
        this.ownerActions = new OwnerActions();
        boolean flag = true;
        while (flag) {
            MenuActions.showMenu();
            Integer input = in.nextInt();
            switch (input) {
                case 1:
                    catActions.addCat();
                    break;

                case 2:
                    catActions.addFriendship();
                    break;

                case 3:
                    ownerActions.addOwner();
                    break;

                case 4:
                    ownerActions.addOwnership();
                    break;

                case 5:
                    catActions.updateCatName();
                    break;

                case 6:
                    ownerActions.updateOwnerName();
                    break;

                case 7:
                    catActions.removeCat();
                    break;

                case 8:
                    catActions.removeFriendship();
                    break;

                case 9:
                    ownerActions.removeOwner();
                    break;

                case 10:
                    ownerActions.removeOwnership();
                    break;

                case 11:
                    catActions.getAllCats();
                    break;

                case 12:
                    catActions.getAllFriendships();
                    break;

                case 13:
                    ownerActions.getAllOwners();
                    break;

                case 14:
                    ownerActions.getAllOwnerships();
                    break;

                case 15:
                    catActions.getAllCatFriendships();
                    break;

                case 16:
                    ownerActions.getAllOwnerOwnerships();
                    break;

                case 17:
                    ownerActions.getCatOwner();
                    break;

                case 18:
                    flag = MenuActions.shutdown();
                    break;
            }
        }
    }
}
