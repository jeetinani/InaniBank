package com.inani.bank;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    KafkaTemplate<String, String> template;

    public MessageProducer(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void send(String message) {
        template.send("ofss", message);
        System.out.println("message sent");
    }
}
