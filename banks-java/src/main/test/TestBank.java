import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.CentralBankImpl;
import tools.BankException;
import tools.ClientException;

import java.util.UUID;

public class TestBank {
    private CentralBankImpl centralBank;

    @Before
    public void SetUp() throws BankException, ClientException {
        centralBank = new CentralBankImpl();
    }
    @Test
    public void RegisterBank() throws BankException {
        centralBank.createBank("VTB", 1.0, 1.0, 1.0);
        Assert.assertTrue(centralBank.findBank("VTB") != null);
    }
    @Test
    public void AddClientToBankClientInBank() throws BankException, ClientException {
        centralBank.createBank("VTB", 1.0, 1.0, 1.0);
        UUID clientId = centralBank.findBank("VTB").createClient("Grisha");
        Assert.assertTrue(centralBank.findBank("VTB").findClient(clientId) != null);
    }
    @Test (expected = ClientException.class)
    public void SubscribeTheSubscriber_CatchException() throws BankException, ClientException {
        centralBank.createBank("VTB", 1.0, 1.0, 1.0);
        UUID clientId = centralBank.findBank("VTB").createClient("Grisha");
        centralBank.findBank("VTB").addSubscriber(centralBank.findBank("VTB").findClient(clientId));
        centralBank.findBank("VTB").addSubscriber(centralBank.findBank("VTB").findClient(clientId));
    }
}