package com.codingtask.messageapp.service;

import com.codingtask.messageapp.model.Message;
import com.codingtask.messageapp.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = AppConstants.KAFKA_MESSAGE_TOPIC, groupId = AppConstants.KAFKA_GROUP_ID)
    public void consumeFromKafkaTopic(Message message) {
        logger.info(String.format("The consumed message is ----> %s", message));
    }
}
