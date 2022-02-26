package ru.itmo.entities;

import ru.itmo.interfaces.BankAccount;
import ru.itmo.tools.MoneyException;
import ru.itmo.tools.PercentException;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CreditAccount implements BankAccount {

    private final UUID clientId;
    private final Double commission;
    private final UUID accountId;
    private final OffsetDateTime lastInterestChargeTime;
    private Double balance;

    public CreditAccount(UUID clientId, Double balance, Double commission) throws PercentException, MoneyException {
        if (commission <= 0)
            throw new PercentException("Invalid commission");
        if (balance <= 0)
            throw new MoneyException("Invalid balance for credit account");
        this.clientId = clientId;
        this.balance = balance;
        this.commission = commission;
        this.accountId = UUID.randomUUID();
        this.lastInterestChargeTime = OffsetDateTime.now();
    }

    public UUID getAccountId() {
        return this.accountId;
    }

    public AccountTypes getAccountType() {
        return AccountTypes.Credit;
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
        OffsetDateTime days = time.minusDays(this.lastInterestChargeTime.getDayOfMonth());
        for (Integer i = 0; days.getDayOfMonth() > i; i++)
            this.balance -= this.balance * commission;
    }
}
