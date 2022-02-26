package interfaces;

import tools.DateException;
import tools.MoneyException;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface BankAccount
{
    String getAccountType();

    UUID getAccountId();

    UUID getClientId();

    Double getBalance();

    void put(Double sum) throws MoneyException;

    void withdraw(Double sum) throws MoneyException, DateException;

    void transfer(Double sum, BankAccount account) throws MoneyException, DateException;

    void skipTime(OffsetDateTime time);
}
