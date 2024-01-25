package com.payment.paymentservice.Dto;

public enum OrderStatus {
    ORDER_RECEIVED("ORDER_RECEIVED"),
    ITEM_IN_STOCK("ITEM_IN_STOCK"),
    ORDER_FULFILLED("ORDER_FULFILLED"),
    ORDER_CREATED("ORDER_CREATED"),
    ORDER_COMPLETED("ORDER_COMPLETED"),
    ORDER_CANCELLED("ORDER_CANCELLED"),
    PAYMENT_COMPLETED("PAYMENT_COMPLETED"),
    PAYMENT_FAILED("PAYMENT_FAILED"),
    PAYMENT_REFUNDED("PAYMENT_REFUNDED"),
    SHIPMENT_CREATED("SHIPMENT_CREATED"),
    SHIPMENT_FAILED("SHIPMENT_FAILED"),
    SHIPMENT_CANCELLED("SHIPMENT_CANCELLED"),
    ITEM_OUT_OF_STOCK("ITEM_OUT_OF_STOCK"),
    ORDER_SHIPPED("ORDER_SHIPPED"),
    ORDER_NOT_FULFILLED("ORDER_NOT_FULFILLED"),
    ORDER_CANCEL_REQUEST("ORDER_CANCEL_REQUEST"),
    PAYMENT_TIMED_OUT("PAYMENT_TIMED_OUT"),
    SHIPMENT_TIMED_OUT("SHIPMENT_TIMED_OUT");

    private final String name;

    private OrderStatus(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}