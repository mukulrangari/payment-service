package com.payment.paymentservice.Dto;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
    @Id
    private String id;
    private String userId;
    private String productId;
    private OrderStatus currentOrderStatus;
    ArrayList<OrderStatus> orderStatusHistory;
}
