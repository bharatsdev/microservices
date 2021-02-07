package com.ms.product.mq;

import com.ms.product.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderReceiver {
    @Autowired
    RabbitMQConfig config;

    @RabbitListener(queues = "${product-service.queue.name}")
    public void receiveMessage(final Product data) {
        log.info("Received Message : {} from order app for product.", data);

//        try {
//            log.info("Make call to Rest api");
//            //TODO Write code to make rest call to api
//        } catch (HttpClientErrorException ex) {
//            log.error(ex.getMessage());
//
//            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
//                log.info("Delay.....!");
//                try {
//                    Thread.sleep(Constants.RETRY_DELAY);
//                } catch (InterruptedException e) {
//                }
//                log.error("Throwing exception, so that message will be requeue in queue...!");
//                throw new RuntimeException();
//            } else {
//                throw new AmqpRejectAndDontRequeueException(ex);
//            }
//        } catch (Exception ex) {
//            log.info("Error occurred, passing message to requeue. {}", ex);
//        }

    }
}
