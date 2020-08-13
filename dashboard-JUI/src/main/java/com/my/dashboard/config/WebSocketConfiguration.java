package com.my.dashboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");  // 메모리 기반 메세지 브로커가 해당 API를 구독하고 있는 클라이언트에게 메세지 전달
		config.setApplicationDestinationPrefixes("/socket");  // 서버에서 클라이언트로부터의 메세지를 받을 API의 PREFIX를 설정
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {  // 클라이언트에서 WebSocket을 연결할 api를 설정한다.
		registry.addEndpoint("/gs-guide-websocket").withSockJS();  // parameter로 넘겨받는 addendpoint 메소드를 통해 여러 endpoint 설정
	}
}
