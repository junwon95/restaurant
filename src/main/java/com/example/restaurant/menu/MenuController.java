package com.example.restaurant.menu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MenuController {
    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepo){
        menuRepository = menuRepo;
    }
    @GetMapping("/menu")
    public String getMenu1(){
        Menu menu = menuRepository.findMenuById(1);
        int price = menu.getPrice();
        return Integer.toString(price);
    }



}
