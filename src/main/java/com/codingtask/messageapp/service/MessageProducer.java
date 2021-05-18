package com.codingtask.messageapp.service;

import com.codingtask.messageapp.model.Message;
import com.codingtask.messageapp.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public String publishToKafkaTopic(Message message) {
        logger.info("Publishing message to kafka ---->");
        kafkaTemplate.send(AppConstants.KAFKA_MESSAGE_TOPIC, message);
        return "Message published successfully!";
    }
}
