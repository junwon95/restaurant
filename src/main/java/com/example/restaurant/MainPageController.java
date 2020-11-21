package com.example.restaurant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MainPageController {
    @GetMapping("/api/hello")
    public String hello(){
        return "핫스왑";
    }
}
