package com.payment.paymentservice.Dto;

import com.payment.paymentservice.Entity.OrderActivity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    String orderId;
    String productId;
    String userId;
    Integer amount;
    String transactionId;
    OrderStatus orderStatus;
}
