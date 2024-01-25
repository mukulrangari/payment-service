package com.payment.paymentservice.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.payment.paymentservice.Dto.OrderEvent;
import com.payment.paymentservice.utils.KafkaUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentPublisher {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(OrderEvent event) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String stringEvent = "";
        try {
            log.info("PaymentPublisher: Publishing message to topic with event -> {}", event);
            stringEvent = ow.writeValueAsString(event);
            log.info("PaymentPublisher: Published message to topic with event -> {}", event);
        } catch (JsonProcessingException e) {
            log.info("OrderEventPublisher: Error --> {} while publishing message to topic with event -> {}",
                    e.getMessage(), event);
        }
        kafkaTemplate.send(KafkaUtils.TOPIC_ORDER, stringEvent);
    }
}
