package entities;

import interfaces.BankAccount;
import tools.DateException;
import tools.MoneyException;
import tools.TransactionException;

import java.util.UUID;

public class Transaction {

    private BankAccount accountWithdraw;
    private BankAccount accountPut;
    private Double sum;
    private UUID operationId;
    private Boolean isCancelled;

    Transaction(BankAccount accountWithdraw, BankAccount accountPut, Double sum){
        this.accountWithdraw = accountWithdraw;
        this.accountPut = accountPut;
        this.sum = sum;
        this.operationId = UUID.randomUUID();
        this.isCancelled = false;
    }

    public BankAccount getAccountWithdraw(){
        return this.accountWithdraw;
    }

    public BankAccount getAccountPut(){
        return this.accountPut;
    }

    public Double getSum(){
        return this.sum;
    }

    public UUID getOperationId(){
        return this.operationId;
    }

    public Boolean getIsCancelled(){
        return this.isCancelled;
    }

    public void cancelTransaction() throws TransactionException, MoneyException, DateException {
        if (isCancelled = true)
            throw new TransactionException("Operation is already have true-cancel status");
        accountPut.withdraw(this.sum);
        accountWithdraw.put(this.sum);
        this.isCancelled = true;
    }
}
