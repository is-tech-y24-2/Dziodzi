package ru.itmo.interfaces;

import ru.itmo.entities.BankImpl;
import ru.itmo.tools.BankException;

import java.time.OffsetDateTime;

public interface CentralBank {

    static BankImpl findBank(String bankName) throws BankException {
        return null;
    }

    void createBank(String name, Double commission, Double limit, Double debitPercent) throws BankException;

    void skipTime(OffsetDateTime date);
}

