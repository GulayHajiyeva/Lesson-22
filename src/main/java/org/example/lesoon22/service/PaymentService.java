package org.example.lesoon22.service;

import org.example.lesoon22.dto.CreatePaymentRequest;
import org.example.lesoon22.dto.PaymentReadResponse;
import org.example.lesoon22.dto.PaymentResponse;
import org.example.lesoon22.exception.InsufficientBalanceException;
import org.example.lesoon22.exception.PaymentNotFoundException;
import org.example.lesoon22.exception.UserNotFoundException;
import org.example.lesoon22.repository.PaymentRepository;
import org.example.lesoon22.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(UserRepository userRepository, PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentResponse createPayment(CreatePaymentRequest request) {

        BigDecimal balance = userRepository.getBalance(request.getUserId());
        BigDecimal newBalance = balance.subtract(request.getAmount());

        Long paymentId = paymentRepository.createPayment(
                request.getUserId(),
                request.getAmount(),
                "PENDING"
        );

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException();
        }

        userRepository.updateBalance(request.getUserId(), newBalance);
        paymentRepository.updateStatus(paymentId, "SUCCESS");

        return new PaymentResponse(paymentId, "SUCCESS", newBalance);
    }

    public List<PaymentReadResponse> getAll() {
        return paymentRepository.findAll();
    }

    public List<PaymentReadResponse> getByUser(Long userId) {
        if (!userRepository.exists(userId)) {
            throw new UserNotFoundException();
        }
        return paymentRepository.findByUserId(userId);
    }

    public PaymentReadResponse getById(Long id) {
        PaymentReadResponse payment = paymentRepository.findById(id);
        if (payment == null) {
            throw new PaymentNotFoundException();
        }
        return payment;
    }
}
