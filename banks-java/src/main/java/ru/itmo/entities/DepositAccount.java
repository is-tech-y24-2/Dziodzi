package ru.itmo.entities;

import ru.itmo.interfaces.BankAccount;
import ru.itmo.tools.DateException;
import ru.itmo.tools.MoneyException;
import ru.itmo.tools.PercentException;

import java.time.OffsetDateTime;
import java.util.UUID;

public class DepositAccount implements BankAccount {

    private final UUID clientId;
    private final Double percent;
    private final OffsetDateTime expirationDate;
    private final UUID accountId;
    private final OffsetDateTime creationTime;
    private final OffsetDateTime lastInterestChargeTime;
    private Double balance;

    public DepositAccount(UUID clientId, Double balance, Double percent, OffsetDateTime expirationDate) throws MoneyException, PercentException, DateException {
        if (percent <= 0)
            throw new PercentException("Invalid commission");
        if (balance <= 0)
            throw new MoneyException("Invalid balance for credit account");
        if (expirationDate.isBefore(OffsetDateTime.now()))
            throw new DateException("Expiration can't be earlier than current date");
        this.clientId = clientId;
        this.balance = balance;
        this.percent = percent;
        this.expirationDate = expirationDate;
        this.accountId = UUID.randomUUID();
        this.creationTime = OffsetDateTime.now();
        this.lastInterestChargeTime = OffsetDateTime.now();
    }

    public UUID getAccountId() {
        return this.accountId;
    }

    public AccountTypes getAccountType() {
        return AccountTypes.Deposit;
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
    public void withdraw(Double sum) throws DateException, MoneyException {
        if (this.expirationDate.isAfter(OffsetDateTime.now()))
            throw new DateException("You can't withdraw before the expiration date");
        if (sum >= this.balance)
            throw new MoneyException("Invalid sum for withdraw");
        this.balance -= sum;
    }

    @Override
    public void transfer(Double sum, BankAccount account) throws MoneyException, DateException {
        if (this.expirationDate.isAfter(OffsetDateTime.now()))
            throw new DateException("You can't transfer before the expiration date");
        this.withdraw(sum);
        account.put(sum);
    }

    @Override
    public void skipTime(OffsetDateTime time) {
        OffsetDateTime days = time.minusDays(this.lastInterestChargeTime.getDayOfMonth());
        for (Integer i = 0; days.getDayOfMonth() > i; i++)
            this.balance += this.balance * percent;
    }
}
