package com.payment.paymentservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.payment.paymentservice.utils.KafkaUtils;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name(KafkaUtils.TOPIC_PAYMENT)
                .build();
    }

    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name(KafkaUtils.TOPIC_ORDER)
                .build();
    }
}