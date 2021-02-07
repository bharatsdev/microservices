package com.ms.order.resources;

import com.ms.order.common.Constants;
import com.ms.order.model.Product;
import com.ms.order.mq.OrderPublisher;
import com.ms.order.mq.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProcessOrderController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitMQConfig config;

    @Autowired
    OrderPublisher sender;

    @GetMapping("/process-order")
    public ResponseEntity<?> sendMessage(@RequestBody Product product) {
        log.info("Place-OrderMethod Invoked...!");
        try {
            String routing = config.getRoutingKey();
            String exchange = config.getTopicExchange();

            sender.sendMessage(rabbitTemplate, exchange, routing, product);
            return ResponseEntity.ok(Constants.IN_QUEUE);
        } catch (Exception ex) {
            log.info("Exception occurred, while sending message to queue :{}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Constants.ERROR_MESSAGE_SENDING_TO_QUEUE);
        }
    }
}

