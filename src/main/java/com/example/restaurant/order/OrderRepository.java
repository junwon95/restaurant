package com.example.restaurant.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Transactional(readOnly = true)
    List<Order> findByTableId(Integer id);

    Order findOrderById(int orderId);
}
