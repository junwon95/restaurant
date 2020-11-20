package com.example.restaurant.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Transactional(readOnly = true)
    Order findOrderById(Integer id);
}
