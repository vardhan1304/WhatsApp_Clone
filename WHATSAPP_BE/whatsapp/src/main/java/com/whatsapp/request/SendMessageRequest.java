package com.whatsapp.request;

import lombok.Data;

@Data
public class SendMessageRequest {


    private Integer userId;
    private Integer chatId;
    private String content;
}
