package com.jolly.rendS.rabbitmq;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RabbitMQProducer {

    private final RabbitTemplate template;

    @Value("${env.RABBITMQ_QUEUE}")
    private String queue;

    private String exchange ="jolly";

    private String routingKey = "jolly.key";

    public void sendMessage(String location) {
        String message = convertToString(location);
        this.template.convertAndSend(exchange, routingKey, message);
    }


    private String convertToString(String location) {
        Gson gson = new Gson();
        return gson.toJson(location);
    }
}
