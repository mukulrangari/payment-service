package com.payment.paymentservice.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("UserBalance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalance {
    @Id
    private String id;
    private Integer amount;
}
