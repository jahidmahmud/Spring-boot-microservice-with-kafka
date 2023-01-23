package com.emailservice.kafka;

import com.emailservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private Logger log= LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    //@KafkaListener(topics = "order_topics",groupId = "email")
    public void consume(OrderEvent event){
        log.info("Email consumer received order event :"+event);

        //save on db
    }
}
