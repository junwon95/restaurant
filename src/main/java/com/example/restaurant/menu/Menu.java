package com.example.restaurant.menu;

import com.example.restaurant.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {
    @Column(name = "menu_name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "stock_price")
    private Integer stockPrice;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "image_file")
    private byte[] imageFile;

}
