package com.payment.paymentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.paymentservice.Entity.UserTransaction;
import com.payment.paymentservice.service.PaymentService;

@RestController
@RequestMapping("api/v1")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/transaction/{orderId}")
    public List<UserTransaction> getTransactionDetails(@PathVariable String orderId) {
        return paymentService.getTransactionDetails(orderId);
    }
}
