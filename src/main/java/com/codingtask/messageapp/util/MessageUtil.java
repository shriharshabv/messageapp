package com.codingtask.messageapp.util;

import com.codingtask.messageapp.dto.MessageDto;
import com.codingtask.messageapp.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageUtil {

    public MessageDto modelToDto(Message message) {
        MessageDto msgDto = new MessageDto();
        msgDto.setId(Long.toString(message.getId()));
        msgDto.setSenderId(Long.toString(message.getSenderId()));
        msgDto.setReceiverId(Long.toString(message.getReceiverId()));
        msgDto.setMessage(message.getMessage());
        return msgDto;
    }

    public List<MessageDto> modelToDto(List<Message> messages) {
        return messages.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public Message dtoToModel(MessageDto msgDto) {
        Message message = new Message();
        message.setId((msgDto.getId() == null || msgDto.getId().isBlank()) ? 0 : Long.parseLong(msgDto.getId()));
        message.setSenderId((msgDto.getSenderId() == null || msgDto.getSenderId().isBlank()) ? 0 : Long.parseLong(msgDto.getSenderId()));
        message.setReceiverId((msgDto.getReceiverId() == null || msgDto.getReceiverId().isBlank()) ? 0 : Long.parseLong(msgDto.getReceiverId()));
        message.setMessage(msgDto.getMessage());
        return message;
    }

    public List<Message> dtoToModel(List<MessageDto> messages) {
        return messages.stream().map(this::dtoToModel).collect(Collectors.toList());
    }
}
