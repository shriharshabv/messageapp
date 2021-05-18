package com.codingtask.messageapp.service;

import com.codingtask.messageapp.model.Message;
import com.codingtask.messageapp.repository.MessageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Captor
    private ArgumentCaptor<Message> messageArgumentCaptor;

    @Test
    @DisplayName("Should save a new message to database")
    void shouldSaveNewMessageToDb() {
        Message newMessage = new Message(0L, 111L, 222L, "Hello World");
        Message expectedMessage = new Message(1L, 111L, 222L, "Hello World");

        Mockito.when(messageRepository.save(newMessage)).thenReturn(expectedMessage);

        Message messageFromDb = messageService.saveMessage(newMessage);

        Mockito.verify(messageRepository, Mockito.times(1)).save(messageArgumentCaptor.capture());
    }

    @Test
    @DisplayName("Should return an existing message by id from database")
    void shouldReturnMessageByIdFromDb() {
        Message expectedMessage = new Message(1L, 111L, 222L, "Hello World");

        Mockito.when(messageRepository.findById(1L)).thenReturn(Optional.of(expectedMessage));

        Message messageFromDb = messageService.getMessage(1L);

        Assertions.assertThat(messageFromDb.getId()).isEqualTo(expectedMessage.getId());
        Assertions.assertThat(messageFromDb.getSenderId()).isEqualTo(expectedMessage.getSenderId());
        Assertions.assertThat(messageFromDb.getReceiverId()).isEqualTo(expectedMessage.getReceiverId());
        Assertions.assertThat(messageFromDb.getMessage()).isEqualTo(expectedMessage.getMessage());
    }

    @Test
    @DisplayName("Should return all messages from database")
    void shouldReturnAllMessagesFromDb() {
        List<Message> messages = Arrays.asList(
                new Message(1L, 111L, 222L, "Hello World"),
                new Message(2L, 111L, 333L, "Hello Alien"),
                new Message(3L, 222L, 111L, "Hello User")
        );

        Mockito.when(messageRepository.findAll()).thenReturn(messages);

        List<Message> messagesFromDb = messageService.getAllMessages();

        Assertions.assertThat(messagesFromDb).isNotNull();
        Assertions.assertThat(messagesFromDb).isNotEmpty();
        Assertions.assertThat(messagesFromDb.size()).isEqualTo(messages.size());
        Assertions.assertThat(messagesFromDb).isEqualTo(messages);
    }

    @Test
    @DisplayName("Should return all user sent messages from database")
    void shouldReturnAllSentMessagesFromDb() {
        List<Message> expectedMessages = Arrays.asList(
                new Message(1L, 111L, 222L, "Hello World"),
                new Message(2L, 111L, 333L, "Hello Alien")
        );

        Mockito.when(messageRepository.findAllBySenderId(111L)).thenReturn(expectedMessages);

        List<Message> messagesFromDb = messageService.getAllSentMessages(111L);

        Assertions.assertThat(messagesFromDb).isNotNull();
        Assertions.assertThat(messagesFromDb).isNotEmpty();
        Assertions.assertThat(messagesFromDb.size()).isEqualTo(expectedMessages.size());
        Assertions.assertThat(messagesFromDb).isEqualTo(expectedMessages);
    }

    @Test
    @DisplayName("Should return all user received messages from database")
    void shouldReturnAllReceivedMessagesFromDb() {
        List<Message> expectedMessages = Arrays.asList(
                new Message(3L, 222L, 111L, "Hello User")
        );

        Mockito.when(messageRepository.findAllByReceiverId(111L)).thenReturn(expectedMessages);

        List<Message> messagesFromDb = messageService.getAllReceivedMessages(111L);

        Assertions.assertThat(messagesFromDb).isNotNull();
        Assertions.assertThat(messagesFromDb).isNotEmpty();
        Assertions.assertThat(messagesFromDb.size()).isEqualTo(expectedMessages.size());
        Assertions.assertThat(messagesFromDb).isEqualTo(expectedMessages);
    }

    @Test
    @DisplayName("Should return all user received messages that were sent by another specific user")
    void shouldReturnAllReceivedMessagesSentByParticularUser() {
        List<Message> expectedMessages = Arrays.asList(
                new Message(2L, 111L, 333L, "Hello Alien")
        );

        Mockito.when(messageRepository.findAllBySenderIdAndReceiverId(111L, 333L)).thenReturn(expectedMessages);

        List<Message> messagesFromDb = messageService.getAllMessages(111L, 333L);

        Assertions.assertThat(messagesFromDb).isNotNull();
        Assertions.assertThat(messagesFromDb).isNotEmpty();
        Assertions.assertThat(messagesFromDb.size()).isEqualTo(expectedMessages.size());
        Assertions.assertThat(messagesFromDb).isEqualTo(expectedMessages);
    }
}