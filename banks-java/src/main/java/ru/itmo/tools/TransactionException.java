package ru.itmo.tools;

public class TransactionException extends RuntimeException {

    public TransactionException(String message) {
        super(message);
    }
}