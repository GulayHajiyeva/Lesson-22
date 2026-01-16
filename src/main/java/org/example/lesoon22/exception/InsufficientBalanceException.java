package org.example.lesoon22.exception;
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("User balance is not enough");
    }
}