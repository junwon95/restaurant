package com.example.restaurant.sale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    Sale findByOrderId(Integer id);
}
