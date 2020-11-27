package com.example.restaurant.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Integer> {
    @Transactional(readOnly = true)
    List<Content> findByOrderId(Integer id);
}
