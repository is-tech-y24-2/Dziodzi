package ru.itmo.interfaces;

import ru.itmo.entities.Client;
import ru.itmo.tools.*;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

public interface Bank {

    public void notify(String message) throws NotificationException;


    public BankAccount findAccount(UUID id) throws AccountException;

    void changeCommission(Double commission) throws PercentException;

    void createPercent(Double percent, Double money) throws MoneyException, PercentException;

    void changeBankLimit(Double limit) throws PercentException;

    UUID addDebitAccount(UUID clientId, Double balance) throws MoneyException, ClientException;

    UUID addDepositAccount(UUID clientId, Double balance, OffsetDateTime date) throws MoneyException, PercentException, DateException, ClientException;

    UUID addCreditAccount(UUID clientId, Double balance) throws MoneyException, PercentException, ClientException;

    ArrayList<BankAccount> showAllClientAccounts(UUID clientId) throws AccountException;

    void changeDepositPercent(Double percent) throws PercentException;

    UUID createClient(String name) throws ClientException;

    Client findClient(UUID clientId) throws ClientException;

    void putMoney(BankAccount account, Double money) throws MoneyException;

    void withdrawMoney(BankAccount account, Double money) throws MoneyException, DateException;

    UUID transferMoney(BankAccount accountFrom, BankAccount accountWhere, Double money) throws MoneyException, DateException;

    void cancelTransaction(UUID transactionId) throws TransactionException, MoneyException, DateException;

    void addSubscriber(Client client) throws ClientException;

    void removeSubscriber(Client client) throws ClientException;

    Double changeClientsPercent(Double sum) throws PercentException;

}
