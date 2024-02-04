package org.matei.soa.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ReceiverApplication {

    private static final String TOPIC_EXCHANGE_NAME = "spring-boot-exchange";

    private static final String QUEUE_NAME = "spring-boot";

    public static void main(String[] args) {
        SpringApplication.run(ReceiverApplication.class, args);
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("soa.#");
    }

    // RabbitMQ-specific approach
    @RabbitListener(queues = {QUEUE_NAME})
    public void receiveMessageFromFanout1(String message) {
        log.info("received message {}", message);
    }

//    // generic amqp approach
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        var methodName = "receiveMessage";
//        return new MessageListenerAdapter(receiver, methodName);
//    }
//
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE_NAME);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
}