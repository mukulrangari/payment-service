package com.payment.paymentservice.Entity;

import java.util.Date;

import com.payment.paymentservice.Dto.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderActivity {
    OrderStatus status;
    Date eventTime;
}
