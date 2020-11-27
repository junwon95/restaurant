package com.example.restaurant.sale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalSaleRepository extends JpaRepository<TotalSale, Integer> {

    TotalSale findTotalSaleById(int i);
}
