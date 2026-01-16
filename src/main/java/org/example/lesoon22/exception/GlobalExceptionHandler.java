package org.example.lesoon22.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> handleBalance() {
        return ResponseEntity.badRequest().body(
                Map.of(
                        "errorCode", "INSUFFICIENT_BALANCE",
                        "message", "User balance is not enough"
                )
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUser() {
        return ResponseEntity.badRequest().body(
                Map.of(
                        "errorCode", "USER_NOT_FOUND",
                        "message", "User not found"
                )
        );
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<?> handlePayment() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        "errorCode", "PAYMENT_NOT_FOUND",
                        "message", "Payment not found"
                )
        );
    }
}
