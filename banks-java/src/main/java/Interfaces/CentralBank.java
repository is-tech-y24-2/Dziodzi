package Interfaces;

import Entities.BankImpl;
import Tools.BankException;

import java.time.OffsetDateTime;

public interface CentralBank {

    void createBank(String name, Double commission, Double limit, Double debitPercent) throws BankException;

    static BankImpl findBank(String bankName) throws BankException {
        return null;
    }

    void skipTime(OffsetDateTime date);
}

