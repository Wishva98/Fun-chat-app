package lk.ijse.dep11.web.controller;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.dep11.web.to.MessageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.Vector;


public class ChatWSController extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessionList = new Vector<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessionList.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            MessageTO recievedMessage = objectMapper.readValue(message.getPayload(), MessageTO.class);

            Set<ConstraintViolation<MessageTO>> validate = localValidatorFactoryBean.getValidator().validate(recievedMessage);
            if (validate.isEmpty()){
                for (WebSocketSession webSocketSession : webSocketSessionList) {
                    if (webSocketSession.equals(session)) continue;
                    if (webSocketSession.isOpen()){
                        webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(recievedMessage)));
                        webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(recievedMessage)));
                    }
                }
            }else {
                session.sendMessage(new TextMessage("Failed to send Message"));
            }

        }catch (JacksonException exp){
            session.sendMessage(new TextMessage("Invalid Json " +  exp) );
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessionList.remove(session);
    }
}
