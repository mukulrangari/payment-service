package com.payment.paymentservice.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.payment.paymentservice.Dto.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("UserTransaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransaction {
    @Id
    private String id;
    private String orderId;
    private String userId;
    private Integer amount;
    private OrderStatus status;
}
