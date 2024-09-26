package com.inani.bank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    MessageProducer messageProducer;

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @GetMapping("/save/{message}")
    public void save(@PathVariable(value = "message") String message) {
        messageProducer.send(message);
    }
}
