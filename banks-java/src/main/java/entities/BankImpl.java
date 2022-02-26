package entities;

import interfaces.Bank;
import interfaces.BankAccount;
import tools.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class BankImpl implements Bank {

    private ArrayList<BankAccount> accounts;
    private ArrayList<PercentsAndSums> percents;
    private ArrayList<Client> clients;
    private ArrayList<Subscriber> subscribers;
    private ArrayList<Transaction> transactions;
    private String name;
    private Double commission;
    private Double limit;
    private Double depositPercent;

    public BankImpl(String name, Double commission, Double limit, Double depositPercent) throws BankException {
        if (name == null)
            throw new BankException("No bank name");
        if (commission <= 0)
            throw new BankException("Invalid commission in bank");
        if (limit <= 0)
            throw new BankException("Invalid limit in bank");
        if (depositPercent <= 0)
            throw new BankException("Invalid debit percent in bank");
        this.name = name;
        this.commission = commission;
        this.limit = limit;
        this.depositPercent = depositPercent;
        accounts = new ArrayList<BankAccount>();
        percents = new ArrayList<PercentsAndSums>();
        clients = new ArrayList<Client>();
        subscribers = new ArrayList<Subscriber>();
        transactions = new ArrayList<Transaction>();
    }

    public String getBankName() {
        return this.name;
    }

    public Double getCommission() {
        return this.commission;
    }

    public Double getLimit() {
        return this.limit;
    }

    public Double getDepositPercent() {
        return this.depositPercent;
    }

    public ArrayList<BankAccount> getAccounts() {
        return this.accounts;
    }

    public ArrayList<PercentsAndSums> getPercents() {
        return this.percents;
    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    public ArrayList<Subscriber> getSubscribers() {
        return this.subscribers;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public void notify(String message) throws NotificationException {
        for (Subscriber subscriber : this.subscribers) {
            subscriber.getClient().addNotification(message);
        }
    }

    public BankAccount findAccount(UUID id) throws AccountException {
        for (BankAccount bankAccount : this.accounts) {
            if (Objects.equals(bankAccount.getAccountId(), id)) {
                return bankAccount;
            }
        }
        throw new AccountException("No account");
    }

    public void changeCommission(Double commission) throws PercentException {
        if (commission <= 0)
            throw new PercentException("Invalid commission to change");
        this.commission = commission;
    }

    public void createPercent(Double percent, Double money) throws MoneyException, PercentException {
        percents.add(new PercentsAndSums(percent, money));
    }

    public void changeBankLimit(Double limit) throws PercentException {
        if (limit <= 0)
            throw new PercentException("Invalid limit to change");
        this.limit = limit;
    }

    public UUID addDebitAccount(UUID clientId, Double balance) throws MoneyException, ClientException {
        return findClient(clientId).createDebitAccount(balance).getAccountId();
    }

    public UUID addDepositAccount(UUID clientId, Double balance, OffsetDateTime date) throws MoneyException, PercentException, DateException, ClientException {
        return findClient(clientId).createDepositAccount(balance, date, this.depositPercent).getAccountId();
    }

    public UUID addCreditAccount(UUID clientId, Double balance) throws MoneyException, PercentException, ClientException {
        return findClient(clientId).createCreditAccount(balance, this.commission).getAccountId();
    }

    public ArrayList<BankAccount> showAllClientAccounts(UUID clientId) throws AccountException {
        ArrayList<BankAccount> clientAccounts = new ArrayList<BankAccount>();
        for (BankAccount currentAccount : this.accounts)
            if (Objects.equals(currentAccount.getClientId(), clientId))
                clientAccounts.add(currentAccount);
        if (clientAccounts.size() == 0) {
            throw new AccountException("There is no account for client with this Id");
        }
        return clientAccounts;
    }

    public void changeDepositPercent(Double percent) throws PercentException {
        if (percent <= 0)
            throw new PercentException("Invalid new deposit percent");
        this.depositPercent = percent;
    }

    public UUID createClient(String name) throws ClientException {
        Client client = new Client(name);
        clients.add(client);
        return client.getClientId();
    }

    public Client findClient(UUID clientId) throws ClientException {
        for (Client client : this.clients)
            if (Objects.equals(client.getClientId(), clientId))
                return client;
        throw new ClientException("There is no client with this Id");
    }

    public void putMoney(BankAccount account, Double money) throws MoneyException {
        account.put(money);
    }


    public void withdrawMoney(BankAccount account, Double money) throws MoneyException, DateException {
        account.withdraw(money);
    }

    public UUID transferMoney(BankAccount accountFrom, BankAccount accountWhere, Double money) throws MoneyException, DateException {
        accountFrom.transfer(money, accountWhere);
        Transaction transaction = new Transaction(accountFrom, accountWhere, money);
        this.transactions.add(transaction);
        return transaction.getOperationId();
    }

    public void cancelTransaction(UUID transactionId) throws TransactionException, MoneyException, DateException {
        findTransaction(transactionId).cancelTransaction();
    }

    public void addSubscriber(Client client) throws ClientException {
        Boolean flag = false;
        for(Subscriber subscriber : this.subscribers)
            if (subscriber.getClient() == client)
                flag = true;
        if (flag)
            throw new ClientException("This client already subscribed");
        this.subscribers.add(client.subscribe());
    }

    public void removeSubscriber(Client client) throws ClientException {
        client.unsubscribe(this.subscribers);
    }

    public Double changeClientsPercent(Double sum) throws PercentException {
        for (Integer i = 0; i < this.percents.size(); i++) {
            if ((sum >= this.percents.get(i).getSum()) && (sum < this.percents.get(i + 1).getSum())) {
                return this.percents.get(i).getPercent();
            }
        }
        throw new PercentException("There is no right borders to change client's percent");
    }

    public Transaction findTransaction(UUID operationId) throws MoneyException, TransactionException, DateException {
        for (Transaction transaction : this.transactions)
            if (Objects.equals(transaction.getOperationId(), operationId))
                return transaction;
            throw new TransactionException("There is no transaction with this Id");
        }


    }
