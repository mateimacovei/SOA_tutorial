package com.matei.soa.sender.rest;

import com.matei.soa.sender.rabbitmq.Sender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private final Sender sender;

    @GetMapping
    public void receiveRestMessage(@RequestParam String message) {
        log.info("received message {}", message);
        sender.sendMessage(message);
    }
}
