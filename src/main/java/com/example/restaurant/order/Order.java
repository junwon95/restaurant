package com.example.restaurant.order;

import com.example.restaurant.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Column(name = "order_date")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate orderDate;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "is_served")
    private Boolean isServed;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "table_id")
    private Integer tableId;

}
