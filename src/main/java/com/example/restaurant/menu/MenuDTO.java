package com.example.restaurant.menu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {
    private String name;

    private Integer price;

    private byte[] imageFile;
}
