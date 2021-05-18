package com.codingtask.messageapp.service;

import com.codingtask.messageapp.model.Message;
import com.codingtask.messageapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) { return messageRepository.save(message); }

    public List<Message> getAllMessages() { return messageRepository.findAll(); }

    public Message getMessage(Long messageId) { return messageRepository.findById(messageId).get(); }

    public List<Message> getAllSentMessages(Long senderId) { return messageRepository.findAllBySenderId(senderId); }

    public List<Message> getAllReceivedMessages(Long receiverId) { return messageRepository.findAllByReceiverId(receiverId); }

    public List<Message> getAllMessages(Long senderId, Long receiverId) { return messageRepository.findAllBySenderIdAndReceiverId(senderId, receiverId); }
}
