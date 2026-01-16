package org.example.lesoon22.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class CreatePaymentRequest {
    private Long userId;
    private BigDecimal amount;
}