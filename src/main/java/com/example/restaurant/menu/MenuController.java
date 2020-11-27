package com.example.restaurant.menu;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MenuController {
    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepo){
        menuRepository = menuRepo;
    }

    @GetMapping("/getMenu")
    public List<Menu> getMenu(){
        List<Menu> menus = menuRepository.findAll();
        return menus;
    }

    @PostMapping("/createMenu")
    void createMenu(@RequestBody MenuDTO menuDTO) {
        Menu menu = new Menu();
        menu.setName(menuDTO.getName());
        menu.setPrice(menuDTO.getPrice());
        menu.setStock(0);
        menu.setImageFile(menuDTO.getImageFile());
        menuRepository.save(menu);
    }

    @PostMapping("/fillStock/{menuId}/{stock}")
    void fillStock(@PathVariable("menuId") int menuId, @PathVariable("stock") int stock) {
        Menu menu = menuRepository.findMenuById(menuId);
        menu.setStock(stock);
        menuRepository.save(menu);
    }



}
