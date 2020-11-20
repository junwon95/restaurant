package com.example.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
    @GetMapping("/api/hello")
    public String hello(){
        return "안녕";
    }
}
