package org.example.lesoon22.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentReadResponse {
    private Long paymentId;
    private Long userId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;

}