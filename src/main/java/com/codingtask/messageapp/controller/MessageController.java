package com.codingtask.messageapp.controller;

import com.codingtask.messageapp.dto.MessageDto;
import com.codingtask.messageapp.exception.DuplicateEntryException;
import com.codingtask.messageapp.exception.EmptyOrInvalidInputException;
import com.codingtask.messageapp.model.Message;
import com.codingtask.messageapp.service.MessageProducer;
import com.codingtask.messageapp.service.MessageService;
import com.codingtask.messageapp.util.AppConstants;
import com.codingtask.messageapp.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUtil messageUtil;

    @PostMapping("/messages/send")
    public ResponseEntity<?> sendMessage(@RequestHeader(value = "user_id") Long userId, @RequestBody MessageDto messageDto) {
        if (messageDto.getReceiverId() == null || messageDto.getReceiverId().isBlank()) {
            throw new EmptyOrInvalidInputException(AppConstants.ERROR_CODE_601, AppConstants.INPUT_CANNOT_BE_NULL_OR_EMPTY);
        }
        messageDto.setSenderId(Long.toString(userId));
        if (messageDto.getSenderId().equals(messageDto.getReceiverId())) {
            throw new DuplicateEntryException(AppConstants.ERROR_CODE_603, AppConstants.OPERATION_NOT_ALLOWED);
        }
        logger.info("Saving message to database....");
        Message message = messageService.saveMessage(messageUtil.dtoToModel(messageDto));
        String kafkaResponse = messageProducer.publishToKafkaTopic(message);
        return new ResponseEntity<>(kafkaResponse, HttpStatus.CREATED);
    }

    @GetMapping("/messages/sent")
    public ResponseEntity<List<MessageDto>> getAllSentMessages(@RequestHeader(value = "user_id") Long userId) {
        List<MessageDto> messages = messageUtil.modelToDto(messageService.getAllSentMessages(userId));
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/messages/received")
    public ResponseEntity<List<MessageDto>> getAllReceivedMessages(@RequestHeader(value = "user_id") Long userId) {
        List<MessageDto> messages = messageUtil.modelToDto(messageService.getAllReceivedMessages(userId));
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/messages/sender/{sender_id}")
    public ResponseEntity<List<MessageDto>> getAllReceivedMessagesFromSender(@RequestHeader(value = "user_id") Long userId, @PathVariable("sender_id") Long senderId) {
        List<MessageDto> messages = messageUtil.modelToDto(messageService.getAllMessages(senderId, userId));
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getAllMessages() {
        List<MessageDto> messages = messageUtil.modelToDto(messageService.getAllMessages());
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<MessageDto> getMessage(@PathVariable("id") Long messageId) {
        MessageDto msgDto = messageUtil.modelToDto(messageService.getMessage(messageId));
        return new ResponseEntity<>(msgDto, HttpStatus.OK);
    }
}
