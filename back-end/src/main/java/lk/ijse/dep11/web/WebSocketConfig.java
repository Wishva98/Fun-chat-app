package lk.ijse.dep11.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.dep11.web.controller.ChatWSController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWSController(),"/api/v1/messages").setAllowedOriginPatterns("*");
    }

    @Bean
    public ChatWSController chatWSController(){
        return new ChatWSController();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
