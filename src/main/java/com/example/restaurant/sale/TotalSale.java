package com.example.restaurant.sale;

import com.example.restaurant.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "total_sales")
public class TotalSale extends BaseEntity {

    @Column(name = "total")
    private Integer total;
}