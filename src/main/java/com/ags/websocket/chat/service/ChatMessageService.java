package com.ags.websocket.chat.service;

import com.ags.websocket.chat.ChatMessage;
import com.ags.websocket.chat.repository.ChatMessageRepository;
import com.ags.websocket.chatroom.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(); // You can create your own dedicated exception
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessage(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, true);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }

}
