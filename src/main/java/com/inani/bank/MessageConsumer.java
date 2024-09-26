package com.inani.bank;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;

@Component
public class MessageConsumer {

    MessageRepository messageRepository;

    public MessageConsumer(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @KafkaListener(topics = { "ofss" }, groupId = "asdf")
    public void listen(String msg) {
        messageRepository.addMessage(msg);
        System.out.println("Message recieved is " + msg);
    }
}
