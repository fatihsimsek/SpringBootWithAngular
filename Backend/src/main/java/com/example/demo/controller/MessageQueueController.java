package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class MessageQueueController {

	private static final String TOPIC = "my_topic";
	
    private static final String groupId = "myConsumer";
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.kafkaTemplate.send(TOPIC, message);
    }
    
    @KafkaListener(topics = TOPIC, groupId = groupId)
    public void listen(String message) {
        System.out.println("Received Messasge in group myConsumer: " + message);
    }
}
