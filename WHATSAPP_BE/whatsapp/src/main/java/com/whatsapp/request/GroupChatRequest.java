package com.whatsapp.request;

import lombok.Data;

import java.util.List;


@Data
public class GroupChatRequest {

    private List<Integer> userIds;
    private String chatName;
    private String chatImage;
}
