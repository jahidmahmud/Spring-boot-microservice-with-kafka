package com.orderservice.controller;

import com.orderservice.dto.Order;
import com.orderservice.dto.OrderEvent;
import com.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }
    @PostMapping("/")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent=new OrderEvent();
        orderEvent.setStatus("Pending");
        orderEvent.setMessage("Your order is pending");
        orderEvent.setOrder(order);
        orderProducer.sendMessage(orderEvent);
        return "Order placed successfully";
    }
}
