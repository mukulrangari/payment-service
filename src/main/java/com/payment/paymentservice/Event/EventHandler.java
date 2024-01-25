package com.payment.paymentservice.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.paymentservice.Dto.OrderEvent;
import com.payment.paymentservice.Dto.OrderStatus;
import com.payment.paymentservice.service.PaymentService;

@Service
public class EventHandler {

    @Autowired
    private PaymentService paymentService;

    public void eventProcceser(OrderEvent orderEvent) {
        switch (orderEvent.getOrderStatus()) {
            case OrderStatus.ITEM_IN_STOCK:
                paymentService.newOrderEvent(orderEvent);
                break;
            case OrderStatus.ITEM_OUT_OF_STOCK:
                paymentService.refundOrderEvent(orderEvent);
                break;
            case OrderStatus.SHIPMENT_FAILED:
                paymentService.refundOrderEvent(orderEvent);
                break;
            case OrderStatus.SHIPMENT_TIMED_OUT:
                paymentService.refundOrderEvent(orderEvent);
                break;
            case OrderStatus.SHIPMENT_CANCELLED:
                paymentService.refundOrderEvent(orderEvent);
                break;
            default:
                break;
        }
    }

}
