package com.example.restaurant.sale;

import com.example.restaurant.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "sale_date")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate orderDate;

    @Column(name = "price")
    private Integer price;
}