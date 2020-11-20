package com.example.restaurant.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepo){
        orderRepository = orderRepo;
    }

    @GetMapping("/test")
    public String testForm(Model model){
        Order order = orderRepository.findOrderById(1);
        model.addAttribute(order);
        return "test";
    }


}
