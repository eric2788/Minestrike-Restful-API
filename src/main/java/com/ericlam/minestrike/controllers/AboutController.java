package com.ericlam.minestrike.controllers;

import com.ericlam.minestrike.dao.AboutRepository;
import com.ericlam.minestrike.modals.About;
import com.ericlam.minestrike.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/about")
public class AboutController {

    private final AboutRepository aboutRepository;

    @Autowired
    public AboutController(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    @GetMapping
    public Iterable<About> getAllStaffs() {
        return aboutRepository.findAll();
    }

    @PostMapping
    public About addStaff(@RequestBody About about) {
        return aboutRepository.save(about);
    }

    @DeleteMapping("{uuid}")
    public Map deleteStaff(@PathVariable String uuid) {
        boolean success;
        if (aboutRepository.findById(uuid).isPresent()) {
            aboutRepository.deleteById(uuid);
            success = true;
        } else {
            success = false;
        }
        return Utils.returnResult(success);
    }

    @PutMapping("{uuid}")
    public About updateStaff(@RequestBody About edited, @PathVariable String uuid) {
        return aboutRepository.findById(uuid).map(about -> {
            about.setContact(edited.getContact());
            about.setPosition(edited.getPosition());
            about.setUsername(edited.getUsername());
            return aboutRepository.save(about);
        }).orElseGet(() -> {
            edited.setUuid(uuid);
            return aboutRepository.save(edited);
        });
    }

}
