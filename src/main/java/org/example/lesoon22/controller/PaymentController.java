package org.example.lesoon22.controller;

import org.example.lesoon22.dto.CreatePaymentRequest;
import org.example.lesoon22.dto.PaymentReadResponse;
import org.example.lesoon22.dto.PaymentResponse;
import org.example.lesoon22.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public PaymentResponse create(@RequestBody CreatePaymentRequest request) {
        return service.createPayment(request);
    }

    @GetMapping
    public List<PaymentReadResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<PaymentReadResponse> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    @GetMapping("/{paymentId}")
    public PaymentReadResponse getById(@PathVariable Long paymentId) {
        return service.getById(paymentId);
    }
}
