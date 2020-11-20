package com.example.restaurant.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Transactional(readOnly = true)
    Menu findMenuById(Integer id);
}
