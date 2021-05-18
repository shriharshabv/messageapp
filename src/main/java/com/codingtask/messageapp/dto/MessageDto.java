package com.codingtask.messageapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {
    private String id;
    private String senderId;
    private String receiverId;
    private String message;
}
