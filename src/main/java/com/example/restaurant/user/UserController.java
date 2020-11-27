package com.example.restaurant.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepo){
        userRepository = userRepo;
    }

    @GetMapping("/login")
    public User login(@RequestBody LoginDTO loginDTO){
        User user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        return user;
    }


}
