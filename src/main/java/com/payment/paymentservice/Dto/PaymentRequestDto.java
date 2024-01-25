package com.payment.paymentservice.Dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {
    private Integer orderId;
    private Integer userId;
    private Integer amount;
}
