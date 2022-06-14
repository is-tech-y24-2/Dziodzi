package ru.itmo.entities;

import ru.itmo.interfaces.BankAccount;
import ru.itmo.tools.MoneyException;

import java.time.OffsetDateTime;
import java.util.UUID;

public class DebitAccount implements BankAccount {

    private final UUID accountId;

    private final UUID clientId;

    private Double balance;

    public DebitAccount(UUID clientId, Double balance) throws MoneyException {
        if (balance <= 0)
            throw new MoneyException("Invalid balance for debit account");
        this.clientId = clientId;
        this.accountId = UUID.randomUUID();
        this.balance = balance;
    }

    public UUID getAccountId() {
        return this.accountId;
    }

    public AccountTypes getAccountType() {
        return AccountTypes.Debit;
    }

    public UUID getClientId() {
        return this.clientId;
    }

    public Double getBalance() {
        return this.balance;
    }

    @Override
    public void put(Double sum) throws MoneyException {
        if (sum <= 0)
            throw new MoneyException("Invalid sum for put");
        this.balance += sum;
    }

    @Override
    public void withdraw(Double sum) throws MoneyException {
        if (sum >= this.balance)
            throw new MoneyException("Invalid sum for withdraw");
        this.balance -= sum;
    }

    @Override
    public void transfer(Double sum, BankAccount account) throws MoneyException {
        this.withdraw(sum);
        account.put(sum);
    }

    @Override
    public void skipTime(OffsetDateTime time) {
    }
}
