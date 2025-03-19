package com.vaishalitech.jrsnacks.service;

import com.vaishalitech.jrsnacks.dto.PaymentInitiateRequest;
import com.vaishalitech.jrsnacks.dto.PaymentInitiateResponse;
import com.vaishalitech.jrsnacks.dto.PaymentVerifyRequest;
import com.vaishalitech.jrsnacks.dto.PaymentVerifyResponse;

public interface PaymentService {
    PaymentInitiateResponse initiatePayment(PaymentInitiateRequest request);
    PaymentVerifyResponse verifyPayment(PaymentVerifyRequest request);
}
