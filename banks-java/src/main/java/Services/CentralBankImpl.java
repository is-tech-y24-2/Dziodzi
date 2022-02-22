package Services;

import Entities.BankImpl;
import Interfaces.BankAccount;
import Interfaces.CentralBank;
import Tools.BankException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class CentralBankImpl implements CentralBank {

    private static CentralBankImpl instance;
    private ArrayList<BankImpl> banks = new ArrayList<>();

    public CentralBankImpl()
    { }

    public static CentralBankImpl getInstance()
    {
        if (instance == null)
            instance = new CentralBankImpl();
        return instance;
    }

    public void createBank(String name, Double commission, Double limit, Double debitPercent) throws BankException {
        banks.add(new BankImpl(name, commission, limit, debitPercent));
    }

    public BankImpl findBank(String bankName) throws BankException {
        for(BankImpl bank : banks) {
            if (Objects.equals(bank.getBankName(), bankName)) {
                return bank;
            }
        }
        throw new BankException("There is no bank with this name");
    }

    public void skipTime(OffsetDateTime date) {
        for(BankImpl bank : this.banks)
            for(BankAccount account : bank.getAccounts())
                account.skipTime(date);
    }
}
