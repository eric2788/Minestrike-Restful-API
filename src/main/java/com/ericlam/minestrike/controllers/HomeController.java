package com.ericlam.minestrike.controllers;

import com.ericlam.minestrike.dao.HomeRepository;
import com.ericlam.minestrike.modals.Home;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final HomeRepository homeRepository;

    @Autowired
    public HomeController(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @GetMapping
    public Home getHomePage() {
        return homeRepository.findById(1).orElseGet(() -> homeRepository.findAll().iterator().next());
    }

    @PutMapping
    public Home editHomePage(@RequestBody Home edited) {
        return homeRepository.findById(1).map(home -> {
            home.setTitle(edited.getTitle());
            home.setContent(edited.getContent());
            return homeRepository.save(home);
        }).orElseGet(() -> homeRepository.save(edited));
    }
}
