package com.example.restaurant.order;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepo){
        orderRepository = orderRepo;
    }


}
