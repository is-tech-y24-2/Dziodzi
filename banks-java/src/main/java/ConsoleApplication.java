import interfaces.BankAccount;
import interfaces.CentralBank;
import services.CentralBankImpl;
import tools.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleApplication {

    private static CentralBankImpl centralBank = CentralBankImpl.getInstance();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws BankException, ClientException, MoneyException, PercentException, DateException, AccountException, TransactionException {
        System.out.print("Welcome to Central Bank Console Application!");

        while (true) {
            outputConsoleMenu();
            String message = in.next();
            switch (message) {
                case "1": {
                    createNewBank();
                    break;
                }

                case "2": {
                    createNewClient();
                    break;
                }

                case "3": {
                    createNewAccount();
                    break;
                }

                case "4": {
                    moneyPutter();
                    break;
                }

                case "5": {
                    moneyWithdrawer();
                    break;
                }


                case "6": {
                    moneyTransfer();
                    break;
                }

                case "7": {
                    transferCanceller();
                    break;
                }

                case "8": {
                    plusPassportData();
                    break;
                }

                case "9": {
                    plusAddress();
                    break;
                }

                case "10": {
                    reverseSubscription();
                    break;
                }

                case "11": {
                    addNewPercents();
                    break;
                }

                case "12": {
                    changeCommission();
                    break;
                }

                case "13": {
                    changeLimit();
                    break;
                }

                case "14": {
                    skipTheTime();
                    break;
                }

                case "15": {
                    showAllOfClientAccounts();
                    break;
                }

                default: {
                    break;
                }
            }
        }
    }

    private static void outputConsoleMenu() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Please enter number to enter one of these menu:");
        System.out.println("===================");
        System.out.println("1. Create bank");
        System.out.println("2. Create client");
        System.out.println("3. Create account");
        System.out.println("===================");
        System.out.println("4. Put money");
        System.out.println("5. Withdraw money");
        System.out.println("6. Transfer money");
        System.out.println("7. Cancel transaction");
        System.out.println("===================");
        System.out.println("8.Add client passport data");
        System.out.println("9. Add client address");
        System.out.println("10. Subscribe/Unsubscribe client");
        System.out.println("===================");
        System.out.println("11. Add percents");
        System.out.println("12. Change commission");
        System.out.println("13. Change limit");
        System.out.println("===================");
        System.out.println("14. Skip time");
        System.out.println("15. Show client's accounts");
        System.out.println("===================");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Type here:");
    }

    private static void createNewBank() throws BankException {
        System.out.println("Enter the name of new bank:");
        String name = in.next();
        System.out.println("Enter commission:");
        Double commission = in.nextDouble();
        System.out.println("Enter limit:");
        Double limit = in.nextDouble();
        System.out.println("Enter debit percent:");
        Double debitPercent = in.nextDouble();
        centralBank.createBank(name, commission, limit, debitPercent);
        System.out.println("New bank was successfully created!");
    }

    // 2
    private static void createNewClient() throws BankException, ClientException {
        System.out.println("Enter the name of bank:");
        String bankName = in.next();
        System.out.println("Enter the name of new client:");
        String name = in.next();
        UUID id = centralBank.findBank(bankName).createClient(name);
        System.out.println("Do you want to add the address?");
        System.out.println("Enter 0 to add address, enter 1 to not add");
        String addressStatus = in.next();
        switch (addressStatus)
        {
            case "0":
            {
                System.out.println("Enter the address of new client:");
                String address = in.next();
                centralBank.findBank(bankName).findClient(id).setAddress(address);
                break;
            }

            case "1":
            {
                break;
            }

            default:
            {
                throw new ClientException("Incorrect enter");
            }
        }

        System.out.println("Do you want to add the passport data?");
        System.out.println("Enter 0 to add address, enter 1 to not add");
        String passportDataStatus = in.next();
        switch (passportDataStatus)
        {
            case "0":
            {
                System.out.println("Enter the passport data of new client:");
                String passportData = in.next();
                CentralBank.findBank(bankName).findClient(id).setPassportData(passportData);
                break;
            }

            case "1":
            {
                break;
            }

            default:
            {
                throw new ClientException("Incorrect enter");
            }
        }

        System.out.println("New client was successfully created!");
        System.out.println("Client's id:" + id);
    }

    // 3
    private static void createNewAccount() throws BankException, MoneyException, ClientException, PercentException, DateException {
        System.out.println("Enter the name of bank:");
        String bankName = in.next();
        System.out.println("Enter the id of client:");
        UUID id = UUID.fromString(in.next());
        System.out.println("Enter the type of account:");
        String type = in.next();
        UUID accountId;
        switch (type)
        {
            case "Debit":
                accountId = centralBank.findBank(bankName).addDebitAccount(id, 0.0);
                System.out.println("New Debit Account was successfully created!");
                System.out.println("Account's id:" + accountId);
                break;
            case "Credit":
                accountId = CentralBank.findBank(bankName).addCreditAccount(id, 0.0);
                System.out.println("New Credit Account was successfully created!");
                System.out.println("Account's id:" + accountId);
                break;
            case "Deposit":
                accountId = CentralBank.findBank(bankName).addDepositAccount(id, 0.0, OffsetDateTime.now());
                System.out.println("New Deposit Account was successfully created!");
                System.out.println("Account's id:" + accountId);
                break;
            default:
                System.out.println("Invalid type. Please, try again!");
                break;
        }
    }

    // 4
    private static void moneyPutter() throws BankException, AccountException, MoneyException {
        System.out.println("Enter the name of bank:");
        String bankName = in.next();
        System.out.println("Enter the id of account:");
        UUID id = UUID.fromString(in.next());
        Double balance = centralBank.findBank(bankName).findAccount(id).getBalance();
        System.out.println("Current balance: " + balance);
        System.out.println("Enter the money:");
        Double sum = in.nextDouble();
        centralBank.findBank(bankName).findAccount(id).put(sum);
        balance += sum;
        System.out.println("Current balance (updated): " + balance);
    }

    // 5
    private static void moneyWithdrawer() throws BankException, AccountException, MoneyException, DateException {
        System.out.println("Enter the name of bank:");
        String bankName = in.next();
        System.out.println("Enter the id of account:");
        UUID id = UUID.fromString(in.next());
        Double balance = centralBank.findBank(bankName).findAccount(id).getBalance();
        System.out.println("Current balance: " + balance);
        System.out.println("Enter the money:");
        Double sum = in.nextDouble();
        centralBank.findBank(bankName).findAccount(id).withdraw(sum);
        balance -= sum;
        System.out.println("Current balance (updated): " + balance);
    }

    // 6
    private static void moneyTransfer() throws BankException, AccountException, MoneyException, DateException {
        System.out.println("Enter the name of bank:");
        String bankName = in.next();
        System.out.println("Enter the id of the sender account:");
        UUID id1 = UUID.fromString(in.next());
        System.out.println("Enter the id of the taker account:");
        UUID id2  = UUID.fromString(in.next());
        Double balance1 = centralBank.findBank(bankName).findAccount(id1).getBalance();
        System.out.println("Current balance (sender): " + balance1);
        Double balance2 = centralBank.findBank(bankName).findAccount(id1).getBalance();
        System.out.println("Current balance (taker): " + balance2);
        System.out.println("Enter the money:");
        Double sum = in.nextDouble();
        centralBank.findBank(bankName).findAccount(id1).transfer(sum, centralBank.findBank(bankName).findAccount(id2));
        balance1 -= sum;
        balance2 += sum;
        System.out.println("Current balance (updated for sender): " + balance1);
        System.out.println("Current balance (updated for taker): " + balance2);
    }

    // 7
    private static void transferCanceller() throws BankException, MoneyException, TransactionException, DateException {
        System.out.println("Enter bank name:");
        String name = in.next();
        System.out.println("Enter the id of transaction:");
        UUID id = UUID.fromString(in.next());
        centralBank.findBank(name).cancelTransaction(id);
        System.out.println("The transaction was successfully cancelled!");
    }

    // 8
    private static void plusAddress() throws BankException, ClientException {
        System.out.println("Enter the name of bank:");
        String bankName = in.next();
        System.out.println("Enter the id of client:");
        UUID id = UUID.fromString(in.next());
        System.out.println("Enter the passport data:");
        String pass = in.next();
        centralBank.findBank(bankName).findClient(id).setAddress(pass);
        System.out.println("Success! New status: " + centralBank.findBank(bankName).findClient(id).getClientStatus());
    }

    // 9
    private static void plusPassportData() throws BankException, ClientException {
        System.out.println("Enter the name of bank:");
        String bankName = in.next();
        System.out.println("Enter the id of client:");
        UUID id = UUID.fromString(in.next());
        System.out.println("Enter the telephone number:");
        String pass = in.next();
        centralBank.findBank(bankName).findClient(id).setPassportData(pass);
        System.out.println("Success! New status: " + centralBank.findBank(bankName).findClient(id).getClientStatus());
    }

    // 10
    private static void reverseSubscription() throws BankException, ClientException {
        System.out.println("Enter the name of bank:");
        String bankName = in.next();
        System.out.println("Enter the id of client:");
        UUID id = UUID.fromString(in.next());
        System.out.println("Do you want to Subscribe or Unsubscribe?");
        String pass = in.next();

        switch (pass)
        {
            case "Subscribe":
                centralBank.findBank(bankName).addSubscriber(centralBank.findBank(bankName).findClient(id));
                break;
            case "Unsubscribe":
                centralBank.findBank(bankName).removeSubscriber(centralBank.findBank(bankName).findClient(id));
                break;
            default:
                System.out.println("Invalid answer. Please, try again!");
                break;
        }
        System.out.println("Success! New status: " + centralBank.findBank(bankName).findClient(id).getNotifications());
    }

    // 11
    private static void addNewPercents() throws BankException, MoneyException, PercentException {
        System.out.println("Enter bank name:");
        String name = in.next();
        System.out.println("Enter new sum:");
        Double sum = in.nextDouble();
        System.out.println("Enter new percent:");
        Double percent = in.nextDouble();
        centralBank.findBank(name).createPercent(percent, sum);
        System.out.println("The new percent was successfully added!");
    }

    // 12
    private static void changeCommission() throws BankException, PercentException {
        System.out.println("Enter bank name:");
        String name = in.next();
        System.out.println("Enter new limit:");
        Double commission = in.nextDouble();
        centralBank.findBank(name).changeCommission(commission);
        System.out.println("The commission was successfully changed!");
    }

    // 13
    private static void changeLimit() throws BankException, PercentException {
        System.out.println("Enter bank name:");
        String name = in.next();
        System.out.println("Enter new limit:");
        Double limit = in.nextDouble();
        centralBank.findBank(name).changeBankLimit(limit);
        System.out.println("The limit was successfully changed!");
    }

    // 14
    private static void skipTheTime() throws DateException {
        System.out.println("Type number of days:");
        Integer days = in.nextInt();
        if (days <= 0)
            throw new DateException("Invalid days");
        OffsetDateTime date = OffsetDateTime.now();
        centralBank.skipTime(date.plusDays(days));
        System.out.println("Super Time Machine has skipped time for about " + days + " days!");
    }

    // 15
    private static void showAllOfClientAccounts() throws BankException, AccountException {
        System.out.println("Enter the name of bank:");
        String bankName = in.next();
        System.out.println("Enter the id of client:");
        UUID id = UUID.fromString(in.next());
        ArrayList<BankAccount> list = centralBank.findBank(bankName).showAllClientAccounts(id);
        System.out.println("--------------------------");
        for(BankAccount i : list)
        {
            System.out.println(i.getAccountType() + " : " + i.getAccountId());
        }

        System.out.println("--------------------------");
        System.out.println("Don't forget to copy account id for your future interactions!");
    }
}
