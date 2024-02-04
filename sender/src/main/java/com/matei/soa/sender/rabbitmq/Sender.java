package com.matei.soa.sender.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Sender {

    private static final String ROUTING_KEY = "soa.tutorial";
    private static final String TOPIC_EXCHANGE_NAME = "spring-boot-exchange";
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY, message);
    }
}
