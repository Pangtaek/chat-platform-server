package com.pangtaek.chatplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
// public class WssConfig implements WebSocketConfigurer {
public class WssConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    // 펍섭에 대해서 명시하는 메서드
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    // 웹소켓 통신에 대해서 연결되는 기본적인 베이스 값
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .setAllowedOrigins("*");
        // .withSockJS();

                // withSockJS(): 클라이언트가 웹소켓을 사용할 수 없는 환경에서 대체로 구현해주는 방어 로직
    }

    // @Override
    // public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    // registry.addHandler(null, "/ws/v1/chat")
    // .setAllowedOrigins("*");
    // }

}
