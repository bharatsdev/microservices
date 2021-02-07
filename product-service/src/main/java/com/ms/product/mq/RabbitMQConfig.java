package com.ms.product.mq;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
@PropertySource("classpath:application.properties")
public class RabbitMQConfig {

    @Value("${product-service.exchange.name}")
    private String topicExchange;

    @Value("${product-service.queue.name}")
    private String queue;

    @Value("${product-service.routing.key}")
    private String routingKey;


    @Bean
    Queue queueName() {
        return new Queue(getQueue());
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(getTopicExchange());
    }

    @Bean
    Binding getBinding() {
        log.info("getBinding....");

        return BindingBuilder.bind(queueName()).to(topicExchange()).with(this.getRoutingKey());
    }

    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
