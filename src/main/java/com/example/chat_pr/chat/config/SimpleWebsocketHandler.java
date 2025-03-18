//package com.example.chat_pr.chat;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class SimpleWebsocketHandler extends TextWebSocketHandler {
//
//    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        sessions.add(session);
//        System.out.println("연결 수립 : " + session.getId());
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String paylaod = message.getPayload();
//        System.out.println("Received Message : " + paylaod);
//
//        for (WebSocketSession s : sessions){
//            if(s.isOpen()){
//                s.sendMessage(new TextMessage(paylaod));
//            }
//        }
//
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//       sessions.remove(session);
//        System.out.println("removed");
//    }
//}
