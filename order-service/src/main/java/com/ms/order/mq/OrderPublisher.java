package com.ms.order.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderPublisher {
    public void sendMessage(RabbitTemplate rabbitTemplate, String exchange, String routingKey, Object data) {
        log.info("Message sending to the Queue using routing key {}. Message.{}", routingKey, data);

        rabbitTemplate.convertAndSend(exchange, routingKey, data);

        log.info("Message processed successfully..!");
    }
}
