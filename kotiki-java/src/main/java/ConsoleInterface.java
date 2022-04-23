import entities.Controller;
import ru.itmo.tools.DaoException;
import ru.itmo.tools.ServiceException;

public class ConsoleInterface {
    public static void main(String[] args) throws ServiceException, DaoException {
        Controller controller = new Controller();
        controller.start();
    }
}
