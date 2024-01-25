package com.payment.paymentservice.Dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private String message;
    private String orderId;
}
