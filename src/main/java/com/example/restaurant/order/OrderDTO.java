package com.example.restaurant.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    Integer tableId;
    String status;
    List<String> menuNames;
    List<Integer> menuCounts;
}
