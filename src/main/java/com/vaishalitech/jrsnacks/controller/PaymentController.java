package com.vaishalitech.jrsnacks.controller;

import com.vaishalitech.jrsnacks.dto.PaymentInitiateRequest;
import com.vaishalitech.jrsnacks.dto.PaymentInitiateResponse;
import com.vaishalitech.jrsnacks.dto.PaymentVerifyRequest;
import com.vaishalitech.jrsnacks.dto.PaymentVerifyResponse;
import com.vaishalitech.jrsnacks.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public ResponseEntity<PaymentInitiateResponse> initiatePayment(@RequestBody PaymentInitiateRequest request) {
        PaymentInitiateResponse response = paymentService.initiatePayment(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<PaymentVerifyResponse> verifyPayment(@RequestBody PaymentVerifyRequest request) {
        PaymentVerifyResponse response = paymentService.verifyPayment(request);
        return ResponseEntity.ok(response);
    }
}
