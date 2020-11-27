package com.example.restaurant.content;

import com.example.restaurant.model.BaseEntity;
import com.example.restaurant.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "contents")
public class Content extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_count")
    private Integer menuCount;

}