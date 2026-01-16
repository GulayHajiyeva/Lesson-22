package org.example.lesoon22.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentResponse {
    private Long paymentId;
    private String status;
    private BigDecimal balance;
}