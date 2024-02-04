package org.matei.soa.receiver.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {
    public void receiveMessage(String message) {
        log.info("received message {}", message);
    }


}
