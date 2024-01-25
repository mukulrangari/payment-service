package com.payment.paymentservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.payment.paymentservice.Dto.OrderEvent;
import com.payment.paymentservice.Dto.OrderStatus;
import com.payment.paymentservice.Dto.PurchaseOrder;
import com.payment.paymentservice.Entity.UserBalance;
import com.payment.paymentservice.Entity.UserTransaction;
import com.payment.paymentservice.Event.PaymentPublisher;
import com.payment.paymentservice.repo.UserBalanceRepository;
import com.payment.paymentservice.repo.UserTransactionRepository;

@Service
public class PaymentService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private PaymentPublisher paymentPublisher;

    @Transactional
    public void newOrderEvent(OrderEvent orderEvent) {
        try {
            final String uri = "http://localhost:8081/api/v1/order/" + orderEvent.getOrderId();
            RestTemplate restTemplate = new RestTemplate();
            // Thread.sleep(30000);
            PurchaseOrder purchaseOrder = restTemplate.getForObject(uri, PurchaseOrder.class);
            if (purchaseOrder.getOrderStatusHistory().contains(OrderStatus.ORDER_CANCELLED)) {
                return;
            }
            UserBalance userBalance = userBalanceRepository.findById(orderEvent.getUserId()).orElse(null);
            if (userBalance.getAmount() > orderEvent.getAmount()) {
                userBalance.setAmount(userBalance.getAmount() - orderEvent.getAmount());
                orderEvent.setOrderStatus(OrderStatus.PAYMENT_COMPLETED);
                userBalanceRepository.save(userBalance);
                userTransactionRepository
                        .save(new UserTransaction(null, orderEvent.getOrderId(), orderEvent.getUserId(),
                                orderEvent.getAmount(), orderEvent.getOrderStatus()));
            } else {
                orderEvent.setOrderStatus(OrderStatus.PAYMENT_FAILED);
                userTransactionRepository
                        .save(new UserTransaction(null, orderEvent.getOrderId(), orderEvent.getUserId(),
                                orderEvent.getAmount(), orderEvent.getOrderStatus()));
            }
            paymentPublisher.sendMessage(orderEvent);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Transactional
    public void refundOrderEvent(OrderEvent orderEvent) {
        // Start with refund if payment is already done or else just cancel the order;
        final String uri = "http://localhost:8081/api/v1/order/" + orderEvent.getOrderId();
        RestTemplate restTemplate = new RestTemplate();
        PurchaseOrder purchaseOrder = restTemplate.getForObject(uri, PurchaseOrder.class);
        if (purchaseOrder.getOrderStatusHistory().contains(OrderStatus.PAYMENT_COMPLETED)
                && !purchaseOrder.getOrderStatusHistory().contains(OrderStatus.PAYMENT_REFUNDED)) {
            UserBalance userBalance = userBalanceRepository.findById(orderEvent.getUserId()).orElse(null);
            userBalance.setAmount(userBalance.getAmount() + orderEvent.getAmount());
            orderEvent.setOrderStatus(OrderStatus.PAYMENT_REFUNDED);
            userBalanceRepository.save(userBalance);
            userTransactionRepository.save(new UserTransaction(null, orderEvent.getOrderId(), orderEvent.getUserId(),
                    orderEvent.getAmount(), orderEvent.getOrderStatus()));
            paymentPublisher.sendMessage(orderEvent);
        } else if (purchaseOrder.getOrderStatusHistory().contains(OrderStatus.ORDER_CANCELLED)) {
            paymentPublisher.sendMessage(orderEvent);
        }
    }

    public List<UserTransaction> getTransactionDetails(String orderId) {
        return userTransactionRepository.findByOrderId(orderId);
    }
}
