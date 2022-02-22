package Entities;

import Interfaces.BankAccount;
import Tools.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Client {

    private final String nameSurname;
    private final UUID clientId;
    private final ArrayList<String> notifications;
    private String address;
    private String passportData;
    private Boolean clientStatus;


    public Client(String nameSurname) throws ClientException {
        if (nameSurname == null)
            throw new ClientException("No client name");
        this.nameSurname = nameSurname;
        clientStatus = false;
        clientId = UUID.randomUUID();
        notifications = new ArrayList<String>();
    }

    public String getNameSurname(){
        return this.nameSurname;
    }

    public Boolean getClientStatus(){
        return this.clientStatus;
    }

    public UUID getClientId(){
        return this.clientId;
    }

    public ArrayList<String> getNotifications(){
        return this.notifications;
    }

    public String getAddress(){
        return this.address;
    }

    public String getPassportData(){
        return this.passportData;
    }

    public void addNotification(String message) throws NotificationException {
        if (message == null)
            throw new NotificationException("No message to notify the client");
        notifications.add(OffsetDateTime.now() + ": " + message);
    }

    public void setAddress (String address) throws ClientException {
        if (address == null)
            throw new ClientException("Empty address data");
        String currentAddress = this.address;
        this.address = address;
        setClientStatus();
    }

    public void setPassportData (String passportData) throws ClientException {
        if (passportData == null)
            throw new ClientException("Empty passport data");
        this.passportData = passportData;
        setClientStatus();
    }

    public void setClientStatus () {
        if (this.passportData != null && (this.address != null))
            clientStatus = true;
    }

    public DebitAccount createDebitAccount(Double balance) throws MoneyException {
        return new DebitAccount(this.clientId, balance);
    }

    public DepositAccount createDepositAccount(Double balance, OffsetDateTime date, Double depositPercent) throws MoneyException, PercentException, DateException {
        return new DepositAccount(this.clientId, balance, depositPercent, date);
    }

    public CreditAccount createCreditAccount(Double balance, Double commission) throws MoneyException, PercentException {
        return new CreditAccount(this.clientId, balance, commission);
    }

    public Subscriber subscribe() throws ClientException {
        return new Subscriber(this);
    }

    public void unsubscribe(ArrayList<Subscriber> list) throws ClientException {
        Boolean flag = false;
        for(Subscriber subscriber : list)
            if (subscriber.getClient() == this)
                list.remove(subscriber);
                flag = true;
        if (!flag)
            throw new ClientException("This client wasn't subscriber");
        }
}