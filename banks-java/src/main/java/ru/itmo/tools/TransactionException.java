package ru.itmo.tools;

public class TransactionException extends BanksException {

    public TransactionException(String message) {
        super(message);
    }
}